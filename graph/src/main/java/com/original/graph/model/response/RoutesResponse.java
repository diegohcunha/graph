package com.original.graph.model.response;

import java.util.List;

import com.original.graph.model.Route;

public class RoutesResponse {
	
	private List<Route> routes;

	public RoutesResponse(List<Route> routes) {
		this.routes = routes;
	}
	
	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
}
