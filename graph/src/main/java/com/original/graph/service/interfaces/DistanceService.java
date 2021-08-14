package com.original.graph.service.interfaces;

import java.util.List;

import com.original.graph.model.jpa.Edge;

public interface DistanceService {

	Integer calculateDistance(List<String> path, List<Edge> edges);
	Integer calculateDistanceSavedGraph(List<String> path, Integer graphId);

}
