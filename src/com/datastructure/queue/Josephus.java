package com.datastructure.queue;

public class Josephus {
	public static <E> CircularQueue<E> buildQueue(E[] data) {
		CircularQueue<E> queue = new LinkedCircularQueue<>();
		for (E e : data) {
			queue.enqueue(e);
		}
		return queue;
	}
	
	public static <E> E getJosephus(CircularQueue<E> queue, int k) {
		if (queue.isEmpty()) {
			return null;
		}
		while (queue.size() > 1) {
			for (int i = 0; i < k - 1; i++) {
				queue.rotate();
			}
			E e = queue.dequeue();
			System.out.println(e + "is out");
		}
		return queue.dequeue();
	}
	
	public static void main(String[] args) {
		String[ ] a1 = {"Alice", "Bob", "Cindy", "Doug", "Ed", "Fred"};
		System.out.println("First winner is " + getJosephus(buildQueue(a1), 3));
	}
	
}	
