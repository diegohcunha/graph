package com.original.graph.model.request;

import java.io.Serializable;
import java.util.List;

import com.original.graph.model.jpa.Edge;

public class GraphRequest implements Serializable{
	private static final long serialVersionUID = 4727301697883052051L;
	
	private List<Edge> data;

	public List<Edge> getData() {
		return data;
	}
	public void setData(List<Edge> data) {
		this.data = data;
	}
}
