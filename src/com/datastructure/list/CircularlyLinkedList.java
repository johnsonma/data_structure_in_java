package com.datastructure.list;

public class CircularlyLinkedList<E> implements List<E> {
	private Node<E> tail;
	private int size;
	
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	@Override
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return tail.getNext().getElement();
	}
	@Override
	public E last() {
		if (isEmpty()) {
			return null;
		}
		return tail.getElement();
	}
	@Override
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		Node<E> head = tail.getNext();
		if (head == tail) {
			tail = null;
		} else {
			tail.setNext(head.getNext());
		}
		size--;
		return head.getElement();
	}
	@Override
	public void addFirst(E e) {
		if (isEmpty()) {
			tail = new Node<>(e, null);
			tail.setNext(tail);
		} else {
			Node<E> node = new Node<>(e, tail.getNext());
			tail.setNext(node);
		}
		size++;
	}
	@Override
	public void addLast(E e) {
		addFirst(e);
		tail = tail.getNext();
	}
	
	
	public void rotate() {
		if (tail != null) {
			tail  = tail.getNext();
		}
	}
	
	
	
}
