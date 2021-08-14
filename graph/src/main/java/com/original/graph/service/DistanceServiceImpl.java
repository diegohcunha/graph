package com.original.graph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.original.graph.exception.ResourceNotFoundException;
import com.original.graph.model.Vertex;
import com.original.graph.model.jpa.Edge;
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
	

}
