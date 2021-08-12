package com.original.graph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.original.graph.model.jpa.Graph;
import com.original.graph.model.request.GraphRequest;
import com.original.graph.service.interfaces.GraphService;

@RestController
@RequestMapping("/graph")
public class GraphController {

	@Autowired
	private GraphService graphService; 
	
	@GetMapping("/{id}")
	public Graph seachGraph(@PathVariable("id") Integer id) {
		return graphService.seachGraph(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Graph newGraph(@RequestBody GraphRequest request) {
		return graphService.persist(request.getData());
	}
	
}
