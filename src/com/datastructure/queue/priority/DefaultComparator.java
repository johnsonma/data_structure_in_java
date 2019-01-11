package com.datastructure.queue.priority;

import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E> {
	@Override
	public int compare(E a, E b) {
		return ((Comparable<E>) a).compareTo(b);
	}

}
