package com.original.graph.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.original.graph.model.jpa.Edge;
import com.original.graph.model.jpa.Graph;
import com.original.graph.service.interfaces.DistanceService;
import com.original.graph.service.interfaces.GraphService;

import io.restassured.http.ContentType;

@WebMvcTest
public class GraphControllerTest {

	@Autowired
	private GraphController graphController;
	
	@MockBean private GraphService graphService;
	@MockBean private DistanceService distanceService;
	@MockBean private RoutesController routesController;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.graphController);
	}
	
	@Test
	public void returnSuccess_whenSeachGraph() {
		
		when(this.graphService.seachGraph(1)).thenReturn(newGraphTest());
		
		given()
			.accept(ContentType.JSON)
			.when()
				.get("/graph/{id}", 1)
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	private Graph newGraphTest() {
		Graph graph = new Graph();
		graph.setId(1);
		List<Edge> edges = new ArrayList<>();
		Edge edge = new Edge();
		edge.setDistance(3);
		edge.setId(1);
		edge.setIdGraph(1);
		edge.setSource("A");
		edge.setTarget("B");
		edges.add(edge);
		
		edge = new Edge();
		edge.setDistance(3);
		edge.setId(2);
		edge.setIdGraph(1);
		edge.setSource("B");
		edge.setTarget("C");
		edges.add(edge);
		graph.setData(edges);
		return graph;
	}
}
