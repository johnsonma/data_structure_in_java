package com.datastructure.list.iterator;

public class FavoritesListMTF<E> extends FavoriteList<E> {
	protected void moveUp(Position<Item<E>> p) {
		if (p != list.first()) {
			list.addFirst(list.remove(p));
		}
	}
	
//	public Iterable<E> getFavorites(int k) throws IllegalArgumentException { 
//		if (k < 0 || k > size()) {
//			throw new IllegalArgumentException("Invalid k");
//		}
//		
//		
//	}

	
	
}
