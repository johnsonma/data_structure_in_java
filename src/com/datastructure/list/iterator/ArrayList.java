package com.datastructure.list.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E>, Iterable<E> {
	private static final int CAPACITY = 16;
	private E[] data;
	private int size;
	
	public ArrayList() {
		this(CAPACITY);
	}
	public ArrayList(int capacity) {
		data = (E[]) new Object[capacity];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E old = data[i];
		data[i] = e;
		return old;
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException {
		checkIndex(i, size + 1);
		if (size == data.length) {
//			throw new IllegalStateException("Array is full");
			resize(2 * data.length);
		}
		for (int k = size - 1; k >= i; k--) {
			data[k + 1] = data[k];
		}
		data[i] = e;
		size++;
	}
	
	public void add(E e) {
		add(size - 1, e);
	}

	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E old = data[i];
		for (int k = i; k < size - 1; k++) {
			data[k] = data[k + 1];
		}
		data[size - 1] = null;
		size--;
		return old;
	}
	
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i  >= n) {
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
	}
	
	protected void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}
	
	
	private class ArrayIterator implements Iterator<E> {
		private int j;
		private boolean removable;

		@Override
		public boolean hasNext() {
			return j < size;
		}

		@Override
		public E next() {
			if (j == size) {
				throw new NoSuchElementException("No next element");
			}
			removable = true;
			return data[j++];
		}

		@Override
		public void remove() {
			if (!removable) {
				throw new IllegalStateException("nothing to remove");
			}
			ArrayList.this.remove(j - 1);
			j--;
			removable = false;
		}
	}
	
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}

}
