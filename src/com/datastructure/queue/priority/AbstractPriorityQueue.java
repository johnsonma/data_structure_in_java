package com.datastructure.queue.priority;

import java.util.Comparator;

import com.datastructure.entry.Entry;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {
	protected static class PQEntry<K, V> implements Entry<K, V> {
		private K k;
		private V v;
		
	    public PQEntry(K k, V v) {
			this.k = k;
			this.v = v;
		}

		@Override
		public K getKey() {
			return k;
		}

		@Override
		public V getValue() {
			return v;
		}

		public void setKey(K k) {
			this.k = k;
		}

		public void setValue(V v) {
			this.v = v;
		}
	}
	
	private Comparator<K> comp;
	protected AbstractPriorityQueue(Comparator<K> c) {
		this.comp = c;
	}
	
	protected AbstractPriorityQueue() {
		this(new DefaultComparator<K>());
	}
	
	protected int compare(Entry<K, V> a, Entry<K, V> b) {
		return comp.compare(a.getKey(), b.getKey());
	}
	
	protected boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return (comp.compare(key, key) == 0); // see if key can be compared
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	
	
	
	
}
