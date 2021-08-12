package com.original.graph.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.original.graph.model.Route;
import com.original.graph.model.request.GraphRequest;
import com.original.graph.model.response.RoutesResponse;
import com.original.graph.service.interfaces.RoutesService;

@RestController
@RequestMapping("/routes")
public class RoutesController {

	@Autowired
	private RoutesService routesService; 
	
	@PostMapping("/from/{town1}/to/{town2}")
	public RoutesResponse calculateRoutes(@PathVariable("town1") String town1, @PathVariable("town2") String town2, @PathParam(value="maxStops") Integer maxStops, @RequestBody GraphRequest request) {
		List<Route> routes = routesService.calculateRoutes(town1, town2, request.getData(), maxStops);
		return new RoutesResponse(routes);
	}
	
}
