package com.datastructure.queue;

import com.datastructure.list.CircularlyLinkedList;

public class LinkedCircularQueue<E> implements CircularQueue<E> {
	private CircularlyLinkedList<E> list = new CircularlyLinkedList<>();
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		list.addLast(e);
	}

	@Override
	public E dequeue() {
		return list.removeFirst();
	}

	@Override
	public E first() {
		return list.first();
	}

	@Override
	public void rotate() {
		list.rotate();
	}
	
}
