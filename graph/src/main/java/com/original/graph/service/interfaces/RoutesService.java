package com.original.graph.service.interfaces;

import java.util.List;

import com.original.graph.model.Route;
import com.original.graph.model.jpa.Edge;

public interface RoutesService {

	List<Route> calculateRoutes(String town1, String town2, List<Edge> edges, Integer maxStops);
	List<Route> calculateRoutesSavedGraph(Integer idGraph, String town1, String town2, Integer maxStops);

}
