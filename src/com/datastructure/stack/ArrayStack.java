package com.datastructure.stack;

public class ArrayStack<E> implements Stack<E> {
	public static final int CAPACITY = 1000;
	private E[] data;
	private int size;
	private int index = -1;
	
	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity];
	}

	@Override
	public int size() {
		return index + 1;
	}

	@Override
	public boolean isEmpty() {
		return index == -1;
	}

	@Override
	public void push(E e) throws IllegalStateException {
		if (size() == data.length) {
			throw new IllegalStateException("Stack is full");
		}
		data[++index] = e;
	}

	@Override
	public E top() {
		if (isEmpty()) {
			return null;
		}
		return data[index];
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E elem = data[index];
		data[index] = null;
		index--;
		return elem;
	}

}
