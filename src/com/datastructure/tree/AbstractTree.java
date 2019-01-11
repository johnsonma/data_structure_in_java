package com.datastructure.tree;

import com.datastructure.list.iterator.Position;

public abstract class AbstractTree<E> implements Tree<E> {
	public boolean isInternal(Position<E> p) {
		return numChildren(p) > 0;
	}
	
	public boolean isExternal(Position<E> p) {
		return numChildren(p) == 0;
	}
	
	public boolean isEmpty(Position<E> p) {
		return size() == 0;
	}
	
	public boolean isRoot(Position<E> p) {
		return root() == p;
	}
	
	public int depth(Position<E> p) {
		if (isRoot(p)) {
			return 0;
		}
		else return 1 + depth(parent(p));
	}
	
	public int height(Position<E> p) {
		int h = 0;
		for (Position<E> c : children(p)) {
			h = Math.max(h, 1 + height(c));
		}
		return h;
	}
	
	
}
