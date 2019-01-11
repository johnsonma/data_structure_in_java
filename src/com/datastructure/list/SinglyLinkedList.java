package com.datastructure.list;

public class SinglyLinkedList<E> implements List<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return head.getElement();
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
		E e = head.getElement();
		head = head.getNext();
		size--;
		if (isEmpty()) {
			tail = null;
		}
		return e;
	}

	@Override
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if (isEmpty()) {
			tail = head;
		}
		size++;
	}

	@Override
	public void addLast(E e) {
		Node<E> node = new Node<>(e, null);
		if (isEmpty()) {
			head = node;
		} else {
			tail.setNext(node);
		}
		tail = node;
		size++;
	}
	
	

}
