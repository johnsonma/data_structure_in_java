package com.datastructure.map;

import java.util.Iterator;

import com.datastructure.entry.Entry;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	protected static class MapEntry<K, V> implements Entry<K, V> {
		private K k;
		private V v;
		
		public MapEntry(K key, V value) {
			k = key;
			v = value;
		}

		public K getKey() {
			return k;
		}

		public void setKey(K k) {
			this.k = k;
		}

		public V getValue() {
			return v;
		}

		public V setValue(V value) {
			V old = v;
			v = value;
			return old;
		}
	}
	
	private class KeyIterator implements Iterator<K> {
		private Iterator<Entry<K, V>> entries = entrySet().iterator();

		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		@Override
		public K next() {
			return entries.next().getKey();
		}
		
		public void remove( ) { throw new UnsupportedOperationException( ); }
	}
	
	private class KeyIterable implements Iterable<K> {

		@Override
		public Iterator<K> iterator() {
			return new KeyIterator();
		}
	}
	
	public Iterable<K> keySet() {
		return new KeyIterable();
	}
	
	private class ValueIterator implements Iterator<V> {
		private Iterator<Entry<K, V>> entries = entrySet().iterator();

		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		@Override
		public V next() {
			return entries.next().getValue();
		}
	}
	
	private class ValueIterable implements Iterable<V> {

		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
	}
	
	public Iterable<V> values() {
		return new ValueIterable();
	}
	
	
	
}
