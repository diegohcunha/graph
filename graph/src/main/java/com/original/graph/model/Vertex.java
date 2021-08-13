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
	
}
