package com.datastructure.graph;

import java.util.HashMap;
import java.util.Map;

import com.datastructure.list.iterator.Position;
import com.datastructure.list.iterator.PositionalList;

public class AdjacencyMapGraph<V, E> implements Graph<V, E> {
	
	private class InnerVertex<V> implements Vertex<V> {
		private V element;
		private Position<Vertex<V>> position;
		private Map<Vertex<V>, Edge<E>> outgoing, incoming;
		
		public InnerVertex(V element, boolean directed) {
			this.element = element;
			outgoing = new HashMap<>();
			if (directed) {
				incoming = new HashMap<>();
			} else {
				incoming = outgoing;
			}
		}

		@Override
		public V getElement() {
			return element;
		}

		public Position<Vertex<V>> getPosition() {
			return position;
		}
		
		public void setPosition(Position<Vertex<V>> p) {
			position = p;
		}


		public Map<Vertex<V>, Edge<E>> getOutgoing() {
			return outgoing;
		}


		public Map<Vertex<V>, Edge<E>> getIncoming() {
			return incoming;
		}
		
		public boolean validate(Graph<V,E> graph) {
			return AdjacencyMapGraph.this == graph && position != null;
		}
	}
	
	private class InnerEdge<E> implements Edge<E> {
		private E element;
		private Position<Edge<E>> position;
		private Vertex<V>[] endpoints;
		
		public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
			element = elem;
			endpoints = (Vertex<V>[]) new Vertex[]{u, v};
		}

		@Override
		public E getElement() {
			return element;
		}

		public Position<Edge<E>> getPosition() {
			return position;
		}

		public Vertex<V>[] getEndpoints() {
			return endpoints;
		}

		public void setPosition(Position<Edge<E>> position) {
			this.position = position;
		}
		
		public boolean validate(Graph<V,E> graph) {
			return AdjacencyMapGraph.this == graph && position != null;
		}
	}
	
	private boolean isDirected;
	private PositionalList<Vertex<V>> vertices;
	private PositionalList<Edge<E>> edges;
	
	public AdjacencyMapGraph(boolean directed) {
		isDirected = directed;
	}
	

	@Override
	public int numVertices() {
		return vertices.size();
	}

	@Override
	public Iterable<Vertex<V>> vertices() {
		return vertices;
	}

	@Override
	public int numEdges() {
		return edges.size();
	}

	@Override
	public Iterable<Edge<E>> edges() {
		return edges;
	}

	@Override
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
		InnerVertex<V> orig = validate(u);
		return orig.getOutgoing().get(v);
	}

	@Override
	public Vertex<V>[] endVertices(Edge<E> e) {
		InnerEdge<E> edge = validate(e);
		return edge.getEndpoints();
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);
		Vertex<V>[] endpoints = edge.getEndpoints();
		if (endpoints[0] == v) {
			return endpoints[1];
		} else if (endpoints[1] == v) {
			return endpoints[0];
		} else
			throw new IllegalArgumentException("v is not incident to this edge");
	}

	@Override
	public int outDegree(Vertex<V> v) {
		InnerVertex<V> vertex = validate(v);
		return vertex.getOutgoing().size();
	}

	@Override
	public int inDegree(Vertex<V> v) {
		InnerVertex<V> vertex = validate(v);
		return vertex.getIncoming().size();
	}

	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) {
		InnerVertex<V> vertex = validate(v);
		return vertex.getOutgoing().values();
	}

	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
		InnerVertex<V> vertex = validate(v);
		return vertex.getIncoming().values();
	}

	@Override
	public Vertex<V> insertVertex(V element) {
		InnerVertex<V> v = new InnerVertex<>(element, isDirected);
		v.setPosition(vertices.addLast(v));
		return v;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) {
		if (getEdge(u, v) == null) {
			InnerEdge<E> edge = new InnerEdge<>(u, v, element);
			edge.setPosition(edges.addLast(edge));
			InnerVertex<V> orig = validate(u);
			InnerVertex<V> dest = validate(v);
			orig.getOutgoing().put(v, edge);
			dest.getIncoming().put(u, edge);
			return edge;
		} else {
			throw new IllegalArgumentException("Edge from u to v exists");
		}
	}

	@Override
	public void removeVertex(Vertex<V> v) {
		InnerVertex<V> vertex = validate(v);
		for (Edge<E> e : vertex.getOutgoing().values()) {
			removeEdge(e);
		}
		for (Edge<E> e : vertex.getIncoming().values()) {
			removeEdge(e);
		}
		vertices.remove(vertex.getPosition());
	}

	@Override
	public void removeEdge(Edge<E> e) {
		InnerEdge<E> edge = validate(e);
//		Iterator<Edge<E>> iterator = edges.iterator();
//		while (iterator.hasNext()) {
//			Edge<E> edge = iterator.next();
//			if (edge == e) {
//				iterator.remove();
//			}
//		}
		edges.remove(edge.getPosition());
	}
	
	private InnerVertex<V> validate(Vertex<V> v) {
		if (!(v instanceof InnerVertex)) {
			throw new IllegalArgumentException("Invalid vertex");
		}
		InnerVertex<V> vertex = (InnerVertex<V>) v;
		if (!vertex.validate(this)) {
			throw new IllegalArgumentException("Invalid vertex");
		}
		return vertex;
	}
	
	private InnerEdge<E> validate(Edge<E> e) {
		if (!(e instanceof InnerEdge)) {
			throw new IllegalArgumentException("Invalid edge");
		}
		InnerEdge<E> edge = (InnerEdge<E>) e;
		if (!edge.validate(this)) {
			throw new IllegalArgumentException("Invalid edge");
		}
		return edge;
	}

}
