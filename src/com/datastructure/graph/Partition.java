package com.datastructure.graph;

import com.datastructure.list.iterator.Position;

public class Partition<E> {
	private class Locator<E> implements Position<E> {
		public E element;
		public int size;
		public Locator<E> parent;
		
		public Locator(E elem) {
			element = elem;
			parent = this;
			size = 1;
		}

		@Override
		public E getElement() throws IllegalStateException {
			return element;
		}
	}
	
	public Position<E> makeCluster(E e) {
		return new Locator<>(e);
	}
	
	public Position<E> find(Position<E> p) {
		Locator<E> loc = validate(p);
		if (loc.parent != loc) {
			loc.parent = (Locator<E>) find(loc.parent);
		}
		return loc.parent;
	}
	
	public void union(Position<E> p, Position<E> q) {
		Locator<E> a = (Locator<E>) find(p);
		Locator<E> b = (Locator<E>) find(q);
		if (a != b) {
			if (a.size > b.size) {
				b.parent = a;
				a.size += b.size;
			} else {
				a.parent = b;
				b.size += a.size;
			}
		}
		
	}
	
	private Locator<E> validate(Position<E> p) {
		if (! (p instanceof Locator)) {
			throw new IllegalArgumentException("not locator");
		}
		return (Locator<E>) p;
	}
	
}
