package com.original.graph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.original.graph.exception.ResourceNotFoundException;
import com.original.graph.model.jpa.Edge;
import com.original.graph.model.jpa.Graph;
import com.original.graph.repository.GraphRepository;
import com.original.graph.service.interfaces.EdgeService;
import com.original.graph.service.interfaces.GraphService;

@Service
public class GraphServiceImpl implements GraphService{

	@Autowired 
	private GraphRepository graphRepository;
	@Autowired 
	private EdgeService edgeService;

	@Override
	public Graph seachGraph(Integer id) {
		Graph graph = graphRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Graph id = " + id));
		
		List<Edge> edges = edgeService.loadByGraph(graph.getId());
		graph.setData(edges);
		return graph;
	}

	@Override
	public Graph persist(List<Edge> edges) {
		Graph graph = new Graph();
		graph = graphRepository.save(graph);
		for(Edge edge : edges){
			edge.setIdGraph(graph.getId());
		}
		edges = edgeService.persist(edges);
		graph.setData(edges);
		return graph;
	}
	
}
