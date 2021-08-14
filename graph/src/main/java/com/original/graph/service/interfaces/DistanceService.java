package com.original.graph.service.interfaces;

import java.util.List;

import com.original.graph.model.jpa.Edge;
import com.original.graph.model.response.DistanceResponse;

public interface DistanceService {

	Integer calculateDistance(List<String> path, List<Edge> edges);
	Integer calculateDistanceSavedGraph(List<String> path, Integer graphId);
	DistanceResponse calculateMinimumDistance(String town1, String town2, List<Edge> data);
	DistanceResponse calculateMinimumDistanceSavedGraph(Integer graphId, String town1, String town2, List<Edge> data);

}
