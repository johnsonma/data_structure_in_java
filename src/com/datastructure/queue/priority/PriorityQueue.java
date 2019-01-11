package com.datastructure.queue.priority;

import com.datastructure.entry.Entry;

public interface PriorityQueue<K, V> {
	int size();
	
	boolean isEmpty();
	
	Entry<K, V> insert(K key, V v) throws IllegalArgumentException;
	
	Entry<K, V> min();
	
	Entry<K, V> removeMin();
	
}
