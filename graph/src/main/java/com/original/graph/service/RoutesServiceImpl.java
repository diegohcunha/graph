package com.original.graph.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.original.graph.exception.ResourceNotFoundException;
import com.original.graph.model.Route;
import com.original.graph.model.Vertex;
import com.original.graph.model.jpa.Edge;
import com.original.graph.service.interfaces.EdgeService;
import com.original.graph.service.interfaces.RoutesService;
import com.original.graph.util.VertexUtil;

@Service
public class RoutesServiceImpl implements RoutesService{
	
	@Autowired
	EdgeService edgeService;
	
	List<Vertex> percorredVertex;
	List<Vertex> totalVertexGraph;
	boolean invalidRoute = false;
	
	@Override
	public List<Route> calculateRoutes(String town1, String town2, List<Edge> edges, Integer maxStops) {
		List<Vertex> vertices = VertexUtil.createVertex(edges);
		totalVertexGraph = VertexUtil.createVertex(edges);
		Vertex vertexInit = null;
		vertexInit = VertexUtil.getVertex(town1, vertices);
		return createRoutes(edges, vertexInit, maxStops, vertices, town2);
	}
	
	@Override
	public List<Route> calculateRoutesSavedGraph(Integer graphId, String town1, String town2, Integer maxStops) {
		List<Edge> edges = edgeService.loadByGraph(graphId);
		
		if(edges == null || edges.isEmpty()){
			throw new ResourceNotFoundException("Graph not found");
		}
		
		return calculateRoutes(town1, town2, edges, maxStops);
		
	}

	private List<Route> createRoutes(List<Edge> edges, Vertex vertexInit, Integer maxStops, List<Vertex> vertices, String town2) {
		percorredVertex = new ArrayList<>();
		List<Route> routes = new ArrayList<>();
		List<Edge> path = new ArrayList<>();
		Edge out;
		
		removeEdgesInFinishVertex(town2, vertices);
		
		percorredVertex.add(vertexInit);
		invalidRoute = false;
		for(int i = 0; i < vertexInit.getOut().size(); i++) {
			out = vertexInit.getOut().get(i);
			path.add(out);
			while(verifyTarget(town2, path.get(path.size() - 1).getTarget(), path, vertices)) {
				System.out.println("building routes");
			}
			
			if(invalidRoute){
				return new ArrayList<>();
			}
			
			if(maxStops == null || path.size() <= maxStops) {
				routes.add(createRouteByPath(path));
			}
			path = new ArrayList<>();
		}
	
		return routes;
	}

	private boolean verifyTarget(String finish, String target, List<Edge> path, List<Vertex> vertices) {
		
		Vertex vertex = VertexUtil.getVertex(target, vertices);
		
		for(Edge edge : vertex.getOut()){
			if(edge.getTarget().equals(finish)){
				path.add(edge);
				return false;
			}else {
				if(verifyIfExistsNonPercorredEdge()){
					percorredVertex.add(vertex);
					path.add(edge);
					verifyTarget(finish, edge.getTarget(), path, vertices);
				}else {
					invalidRoute = true;
					break;
				}
			}
		}
		return false;
	}

	private boolean verifyIfExistsNonPercorredEdge() {
		for(Vertex vertex : totalVertexGraph) {
			if(! percorredVertex.contains(vertex)) {
				return true;
			}
		}
		return false;
	}

	private void removeEdgesInFinishVertex(String finishRoute, List<Vertex> vertices) {
		Vertex vertex = VertexUtil.getVertex(finishRoute, vertices);
		totalVertexGraph.remove(vertex);
	}

	private Route createRouteByPath(List<Edge> path) {
		String strRoute;
		Integer stops = 0;
		strRoute = path.get(0).getSource();
		for(Edge edge : path){
			strRoute += edge.getTarget();
			stops++;
		}
		return new Route(strRoute, stops);
	}

}
