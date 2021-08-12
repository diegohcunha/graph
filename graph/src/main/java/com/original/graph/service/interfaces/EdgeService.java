package com.original.graph.service.interfaces;

import java.util.List;

import com.original.graph.model.jpa.Edge;

public interface EdgeService {

	List<Edge> loadByGraph(Integer idGraph);
	List<Edge> persist(List<Edge> edges);

}
