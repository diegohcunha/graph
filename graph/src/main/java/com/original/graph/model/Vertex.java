package com.original.graph.model;

import java.util.ArrayList;
import java.util.List;

import com.original.graph.model.jpa.Edge;

public class Vertex {
	
	private String source;
	private List<Edge> in;
	private List<Edge> out;
	private boolean visited;

	public Vertex(String source) {
		this.source = source;
		this.visited = false;
		this.in = new ArrayList<>();
		this.out = new ArrayList<>();
	}

	public void addEdgeIn(Edge edge) {
		this.in.add(edge);
	}
	
	public void addEdgeOut(Edge edge) {
		this.out.add(edge);
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public List<Edge> getIn() {
		return in;
	}
	public void setIn(List<Edge> in) {
		this.in = in;
	}
	public List<Edge> getOut() {
		return out;
	}
	public void setOut(List<Edge> out) {
		this.out = out;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((in == null) ? 0 : in.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (in == null) {
			if (other.in != null)
				return false;
		} else if (!in.equals(other.in))
			return false;
		return true;
	}
	
}
