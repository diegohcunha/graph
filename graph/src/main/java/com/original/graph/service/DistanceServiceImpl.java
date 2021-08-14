package com.original.graph.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.original.graph.exception.ResourceNotFoundException;
import com.original.graph.model.Route;
import com.original.graph.model.Vertex;
import com.original.graph.model.jpa.Edge;
import com.original.graph.model.response.DistanceResponse;
import com.original.graph.service.interfaces.DistanceService;
import com.original.graph.service.interfaces.EdgeService;
import com.original.graph.service.interfaces.RoutesService;
import com.original.graph.util.VertexUtil;

@Service
public class DistanceServiceImpl implements DistanceService {

	@Autowired
	EdgeService edgeService;
	
	@Autowired
	RoutesService routesService;
	
	@Autowired
	DistanceService distanceService;
	
	@Override
	public Integer calculateDistance(List<String> path, List<Edge> edges) {
		List<Vertex> vertexList = VertexUtil.createVertex(edges);
		Vertex start = VertexUtil.getVertex(path.get(0), vertexList);
		Integer distance = 0;
		Integer visitedVertex = 1;
		for(int i = 1; i < path.size(); i++) {
			for(Edge edge : start.getOut()){
				if(edge.getTarget().equals(path.get(i))){
					start = VertexUtil.getTarget(edge.getTarget(), vertexList);
					distance += edge.getDistance();
					visitedVertex++;
					break;
				}
			}
		}
		//incomplete path
		if(visitedVertex != path.size()) {
			return -1;
		}
		return distance;
	}

	@Override
	public Integer calculateDistanceSavedGraph(List<String> path, Integer graphId) {
		List<Edge> edges = edgeService.loadByGraph(graphId);
		
		if(edges == null || edges.isEmpty()){
			throw new ResourceNotFoundException("Graph not found");
		}
		
		return calculateDistance(path, edges);
	}

	@Override
	public DistanceResponse calculateMinimumDistance(String town1, String town2, List<Edge> data) {
		if(town1.equals(town2)) {
			return new DistanceResponse(0);
		}
		
		List<Route> routes = routesService.calculateRoutes(town1, town2, data, null);
		Integer minDistance = null;
		Integer distanceCalc;
		List<String> pathCalc;
		List<String> minPath = null; 
		for(Route route : routes) {
			pathCalc = parseRouteToArray(route);
			distanceCalc = calculateDistance(pathCalc, data);
			if(minDistance == null) {
				minDistance = distanceCalc;
				minPath = pathCalc;
			}else if(distanceCalc < minDistance){
				minDistance = distanceCalc;
				minPath = pathCalc;
			}
			
		}
		return new DistanceResponse(minDistance, minPath);
	}
	
	@Override
	public DistanceResponse calculateMinimumDistanceSavedGraph(Integer graphId, String town1, String town2,
			List<Edge> data) {
		List<Edge> edges = edgeService.loadByGraph(graphId);
		
		if(edges == null || edges.isEmpty()){
			throw new ResourceNotFoundException("Graph not found");
		}
		return calculateMinimumDistance(town1, town2, data);
	}

	private List<String> parseRouteToArray(Route route) {
		List<String> path = new ArrayList<>();
		for(char c : route.getRoute().toCharArray()) {
			path.add(String.valueOf(c));
		}
		return path;
	}

}
