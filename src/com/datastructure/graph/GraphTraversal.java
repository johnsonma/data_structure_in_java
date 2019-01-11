package com.datastructure.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.datastructure.entry.Entry;
import com.datastructure.list.iterator.LinkedPositionalList;
import com.datastructure.list.iterator.Position;
import com.datastructure.list.iterator.PositionalList;
import com.datastructure.queue.priority.HeapPriorityQueue;
import com.datastructure.queue.priority.PriorityQueue;
import com.datastructure.stack.LinkedStack;
import com.datastructure.stack.Stack;

public class GraphTraversal {
	
	public static <V, E> void dfs(Graph<V, E> g, Vertex<V> s, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
		known.add(s);
		for (Edge<E> e : g.outgoingEdges(s)) {
			Vertex<V> v = g.opposite(s, e);
			if (!known.contains(v)) {
				forest.put(v, e);
				dfs(g, v, known, forest);
			}
		}
	}
	
	public static <V, E> void bfs(Graph<V, E> g, Vertex<V> s, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
		PositionalList<Vertex<V>> level = new LinkedPositionalList<>();
		known.add(s);
		level.addLast(s);
		while (!level.isEmpty()) {
			PositionalList<Vertex<V>> nextLevel = new LinkedPositionalList<>();
			for (Vertex<V> u : level) {
				for (Edge<E> e : g.outgoingEdges(u)) {
					Vertex<V> v = g.opposite(u, e);
					if (!known.contains(v)) {
						known.add(v);
						forest.put(v, e);
						nextLevel.addLast(v);
					}
				}
			}
			level = nextLevel;
		}
	}
	
	public static <V, E> PositionalList<Edge<E>> constructPath(Graph<V, E> g, Vertex<V> u, Vertex<V> v, Map<Vertex<V>, Edge<E>> forest) {
		PositionalList<Edge<E>> path = new LinkedPositionalList<>();
		if (forest.get(v) != null) {
			Vertex<V> walk = v;
			while (walk != u) {
				Edge<E> edge = forest.get(v);
				path.addFirst(edge);
				walk = g.opposite(walk, edge);
			}
		}
		return path;
	}
	
	public static <V, E> Map<Vertex<V>, Edge<E>> dfsComplete(Graph<V, E> g) {
		Set<Vertex<V>> known = new HashSet<>();
		Map<Vertex<V>, Edge<E>> forest = new HashMap<>();
		for (Vertex<V> u : g.vertices()) {
			if (!known.contains(u));
			dfs(g, u, known, forest);
		}
		return forest;
	}
	
	public static <V, E> void transitiveClosure(Graph<V, E> g) {
		for (Vertex<V> k : g.vertices()) {
			for (Vertex<V> i : g.vertices()) {
				if (i != k && g.getEdge(i, k) != null) {
					for (Vertex<V> j : g.vertices()) {
						if (j != i && j != k && g.getEdge(k, j) != null) {
							if (g.getEdge(i, j) == null) {
								g.insertEdge(i, j, null);
							}
						}
					}
				}
			}
		}
	}
	
	public static <V, E> PositionalList<Vertex<V>> topologicalSort(Graph<V, E> g) {
		PositionalList<Vertex<V>> topo = new LinkedPositionalList<>();
		//container of vertices that have no remaining constraints
		Stack<Vertex<V>> ready = new LinkedStack<>();
		// map keeping track of remaining in-degree for each vertex
		Map<Vertex<V>, Integer> inCount = new HashMap<>();
		
		for (Vertex<V> u : g.vertices()) {
			inCount.put(u, g.inDegree(u));
			if (inCount.get(u) == 0) {
				ready.push(u);
			}
		}
		
		while (!ready.isEmpty()) {
			Vertex<V> u = ready.pop();
			topo.addLast(u);
			for (Edge<E> e : g.outgoingEdges(u)) {
				Vertex<V> v = g.opposite(u, e);
				inCount.put(v, inCount.get(v) - 1);
				if (inCount.get(v) == 0) {
					ready.push(v);
				}
			}
		}
		return topo;
	}
	
	public static <V> Map<Vertex<V>, Integer> shortestLengthPaths(Graph<V, Integer> g, Vertex<V> src) {
		//d.get(v) is upper bound on distance from src to v
		Map<Vertex<V>, Integer> d = new HashMap<>();
		//map reachable v to its d value
		Map<Vertex<V>, Integer> cloud = new HashMap<>();
		//pq will have vertices as elements, with d.get(v) as key
		PriorityQueue<Integer, Vertex<V>> pq = new HeapPriorityQueue<>();
		
		
		for (Vertex<V> v : g.vertices()) {
			if (v == src) {
				d.put(v, 0);
			} else {
				d.put(v, Integer.MAX_VALUE);
			}
			pq.insert(d.get(v), v);
		}
		
		while (!pq.isEmpty()) {
			Entry<Integer, Vertex<V>> entry = pq.removeMin();
			int key = entry.getKey();
			Vertex<V> u = entry.getValue();
			cloud.put(u, key);
			
			for (Edge<Integer> e : g.outgoingEdges(u)) {
				Vertex<V> v = g.opposite(u, e);
				if (cloud.get(v) == null) {
					int weight = e.getElement();
					//relaxation
					if (d.get(u) + weight < d.get(v)) {
						d.put(v, d.get(u) + weight);
						//if the node in pq to be unique may use below:
						//pq.replaceKey(pqTokens.get(v), d.get(v));
						pq.insert(d.get(v), v);
					}
				}
			}
		}
		return cloud;
	}
	
	//Kruskal’s
	public static <V> PositionalList<Edge<Integer>> kruskal(Graph<V, Integer> g) {
		PositionalList<Edge<Integer>> tree = new LinkedPositionalList<>();
		PriorityQueue<Integer, Edge<Integer>> pq = new HeapPriorityQueue<>();
		Partition<Vertex<V>> forest = new Partition<>();
		
		Map<Vertex<V>, Position<Vertex<V>>> positions = new HashMap<>();
		
		for (Vertex<V> v : g.vertices()) {
			positions.put(v, forest.makeCluster(v));
		}
		
		for (Edge<Integer> e : g.edges()) {
			pq.insert(e.getElement(), e);
		}
		
		int size = g.numVertices();
		while (tree.size() != size - 1 && !pq.isEmpty()) {
			Entry<Integer, Edge<Integer>> entry = pq.removeMin();
			Edge<Integer> edge = entry.getValue();
			Vertex<V>[] endPoints = g.endVertices(edge);
			Position<Vertex<V>> a = forest.find(positions.get(endPoints[0]));
			Position<Vertex<V>> b = forest.find(positions.get(endPoints[1]));
			if (a != b) {
				tree.addLast(edge);
				forest.union(a, b);
			}
		}
		return tree;
	}

}
