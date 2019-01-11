package com.datastructure.list;

public interface List<E> {
	public int size();
	
	public boolean isEmpty();
	
	public E first();
	
	public E last();
	
	public E removeFirst();
	
	public void addFirst(E e);
	
	public void addLast(E e);
}
