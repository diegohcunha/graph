package com.original.graph.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.original.graph.model.Route;
import com.original.graph.model.Vertex;
import com.original.graph.model.jpa.Edge;
import com.original.graph.service.interfaces.RoutesService;
import com.original.graph.service.interfaces.VertexService;

@Service
public class RoutesServiceImpl implements RoutesService{
	
	@Autowired
	private VertexService vertexService;

	@Override
	public List<Route> calculateRoutes(String town1, String town2, List<Edge> edges, Integer maxStops) {
		List<Route> routes = new ArrayList<>();
		List<Vertex> vertices = vertexService.createVertex(edges);
		Vertex vertexInit = null;
		
		vertexInit = getVertex(town1, vertices);
		
		List<Edge> path = new ArrayList<>();
		
		Edge out;
		for(int i = 0; i < vertexInit.getOut().size(); i++) {
			out = vertexInit.getOut().get(i);
			path.add(out);
			while(verifyTarget(town2, path.get(path.size() - 1).getTarget(), path, vertices)) {
				System.out.println("building routes");
			}
			if(maxStops > 0 && path.size() <= maxStops) {
				routes.add(createRouteByPath(path));
			}
			path = new ArrayList<>();
		}
	
		return routes;
	}

	private boolean verifyTarget(String finish, String target, List<Edge> path, List<Vertex> vertices) {
		Vertex vertex = getVertex(target, vertices);
		for(Edge edge : vertex.getOut()){
			if(edge.getTarget().equals(finish)){
				path.add(edge);
				return false;
			}else {
				path.add(edge);
				verifyTarget(finish, edge.getTarget(), path, vertices);
			}
		}
		return false;
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
	
	private Vertex getVertex(String source, List<Vertex> vertices) {
		for(Vertex vertex : vertices){
			if(vertex.getSource().equals(source)) {
				return vertex;
			}
		}
		return null;
	}
	
}
