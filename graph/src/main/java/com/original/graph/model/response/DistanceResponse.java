package com.original.graph.model.response;

import java.util.List;

public class DistanceResponse extends BaseResponse{

	private Integer distance;
	private List<String> path;

	public DistanceResponse(Integer distance) {
		this.distance = distance;
	}
	
	public DistanceResponse(Integer distance, List<String> path) {
		this.distance = distance;
		this.path = path;
	}
	
	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}
}
