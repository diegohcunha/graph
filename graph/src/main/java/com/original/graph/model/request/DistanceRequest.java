package com.original.graph.model.request;

import java.io.Serializable;
import java.util.List;

import com.original.graph.model.jpa.Edge;

public class DistanceRequest implements Serializable{
	private static final long serialVersionUID = 4727301697883052051L;
	
	private List<String> path;
	private List<Edge> data;

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	public List<Edge> getData() {
		return data;
	}

	public void setData(List<Edge> data) {
		this.data = data;
	}
}
