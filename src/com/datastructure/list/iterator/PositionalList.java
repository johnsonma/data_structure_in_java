package com.datastructure.list.iterator;

import java.util.Iterator;

public interface PositionalList<E> extends Iterable<E>{
	int size();
	
	boolean isEmpty();
	
	Position<E> first();
	
	Position<E> last();
	
	Position<E> addFirst(E e);
	
	Position<E> addLast(E e);
	
	Position<E> before(Position<E> p) throws IllegalArgumentException;
	
	Position<E> after(Position<E> p) throws IllegalArgumentException;
	
	Position<E> addBefore(Position<E> p, E e);
	
	Position<E> addAfter(Position<E> p, E e);
	
	E set(Position<E> p, E e);
	
	E remove(Position<E> p);
	
	Iterator<E> iterator();
	
}
