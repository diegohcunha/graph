package com.original.graph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.original.graph.model.Route;
import com.original.graph.model.jpa.Edge;
import com.original.graph.service.interfaces.GraphService;
import com.original.graph.service.interfaces.RoutesService;

@Service
public class RoutesServiceImpl implements RoutesService{

	@Autowired
	private GraphService graphService;

	@Override
	public List<Route> calculateRoutes(String town1, String town2, List<Edge> edges, Integer maxStops) {
		return null;
	}
	
}
