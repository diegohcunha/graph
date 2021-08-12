package com.original.graph.service.interfaces;

import java.util.List;

import com.original.graph.model.jpa.Edge;
import com.original.graph.model.jpa.Graph;

public interface GraphService {

	Graph seachGraph(Integer id);
	Graph persist(List<Edge> edges);

}
