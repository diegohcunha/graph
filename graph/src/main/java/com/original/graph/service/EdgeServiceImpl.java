package com.original.graph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.original.graph.model.jpa.Edge;
import com.original.graph.repository.EdgeRepository;
import com.original.graph.service.interfaces.EdgeService;

@Service
public class EdgeServiceImpl implements EdgeService {

	@Autowired
	private EdgeRepository edgeRepository;

	@Override
	public List<Edge> loadByGraph(Integer idGraph) {
		return edgeRepository.loadByGraph(idGraph);
	}

	@Override
	public List<Edge> persist(List<Edge> edges) {
		return edgeRepository.saveAll(edges);
	}
	
}
