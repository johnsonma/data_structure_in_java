package com.datastructure.queue;

public class ArrayQueue<E> implements Queue<E> {
	private E[] data;
	private int f;
	private int size;

	
	public ArrayQueue(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void enqueue(E e) {
		if (size == data.length) {
			throw new IllegalStateException("Queue is full");
		}
		int pos = (f + size) % data.length;
		data[pos] = e;
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			return null;
		}
		E e = data[f];
		data[f] = null;
		f = (f + 1) % data.length;
		size--;
		return e;
	}

	@Override
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return data[f];
	}
	
}
