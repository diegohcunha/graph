package com.original.graph.model;

public class Route {
	private String route;
	private Integer stops;
	
	public Route(String strRoute, Integer stops) {
		this.route = strRoute;
		this.stops = stops;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public Integer getStops() {
		return stops;
	}
	public void setStops(Integer stops) {
		this.stops = stops;
	}
}
