package com.datastructure.list;

import com.datastructure.queue.Deuque;

public class DoublyLinkedList<E> implements List<E>, Deuque<E> {
	private static class Node<E> {
		private E element;
		private Node<E> next;
		private Node<E> previous;
		
		public Node(E e, Node<E> p, Node<E> n) {
			this.element = e;
			this.previous = p;
			this.next = n;
		}

		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public Node<E> getPrevious() {
			return previous;
		}

		public void setPrevious(Node<E> previous) {
			this.previous = previous;
		}
	}
	
	private Node<E> header;
	private Node<E> trailer;
	private int size;
	
	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
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
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return header.getNext().getElement();
	}

	@Override
	public E last() {
		if (isEmpty()) {
			return null;
		}
		return trailer.getPrevious().getElement();
	}
	
	public void addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> node = new Node<>(e, pred, succ);
		pred.setNext(node);
		succ.setPrevious(node);
		size++;
	}

	@Override
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		return remove(header.getNext());
	}
	
	public E removeLast() {
		if (isEmpty()) {
			return null;
		}
		return remove(trailer.getPrevious());
	}

	@Override
	public void addFirst(E e) {
		addBetween(e, header, header.getNext());
	}

	@Override
	public void addLast(E e) {
		addBetween(e, trailer.getPrevious(), trailer);
	}
	
	private E remove(Node<E> node) {
		Node<E> pred = node.getPrevious();
		Node<E> succ = node.getNext();
		pred.setNext(succ);
		succ.setPrevious(pred);
		size--;
		return node.getElement();
	}
}
