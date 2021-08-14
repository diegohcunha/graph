package com.original.graph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.original.graph.model.request.DistanceRequest;
import com.original.graph.model.response.DistanceResponse;
import com.original.graph.service.interfaces.DistanceService;

@RestController
@RequestMapping("/distance")
public class DistanceController {

	@Autowired
	private DistanceService distanceService; 
	
	@PostMapping
	public DistanceResponse calculateDistance(@RequestBody DistanceRequest requestPath) {
		if(requestPath.getPath() == null || requestPath.getPath().isEmpty()) {
			return new DistanceResponse(0);
		}
		Integer distance = distanceService.calculateDistance(requestPath.getPath(), requestPath.getData());
		return new DistanceResponse(distance);
	}
	
	@PostMapping("/{graphId}")
	public DistanceResponse calculateDistanceSavedGraph(@PathVariable("graphId") Integer graphId, @RequestBody DistanceRequest requestPath) {
		Integer distance = distanceService.calculateDistanceSavedGraph(requestPath.getPath(), graphId);
		return new DistanceResponse(distance);
	}
}
