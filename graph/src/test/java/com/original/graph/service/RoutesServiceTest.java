package com.original.graph.service;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.original.graph.controller.RoutesController;
import com.original.graph.model.Route;
import com.original.graph.model.jpa.Edge;
import com.original.graph.service.interfaces.DistanceService;
import com.original.graph.service.interfaces.EdgeService;
import com.original.graph.service.interfaces.GraphService;
import com.original.graph.service.interfaces.RoutesService;

@WebMvcTest
public class RoutesServiceTest {

	@InjectMocks
	private RoutesService routesService = new RoutesServiceImpl();
	
	@MockBean DistanceService distanceService; 
	@MockBean GraphService graphService;
	@MockBean EdgeService edgeService;
	@MockBean RoutesController routesController;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.routesService);
	}
	
	@Test
	public void validateResult_whenCalculateRoute() {
		
		List<Route> routes = this.routesService.calculateRoutes("A", "C", listEdgesTest(), 3);
		
		Assertions.assertEquals(routes.get(0).getRoute(), "ABC");
		Assertions.assertEquals(routes.get(1).getRoute(), "ADC");
		Assertions.assertEquals(routes.get(2).getRoute(), "AEBC");
	}
	

	private List<Edge> listEdgesTest() {
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
		
		edge = new Edge();
		edge.setDistance(3);
		edge.setId(3);
		edge.setIdGraph(1);
		edge.setSource("C");
		edge.setTarget("D");
		edges.add(edge);
		
		edge = new Edge();
		edge.setDistance(3);
		edge.setId(4);
		edge.setIdGraph(1);
		edge.setSource("D");
		edge.setTarget("C");
		edges.add(edge);
		
		edge = new Edge();
		edge.setDistance(3);
		edge.setId(5);
		edge.setIdGraph(1);
		edge.setSource("D");
		edge.setTarget("E");
		edges.add(edge);
		
		edge = new Edge();
		edge.setDistance(3);
		edge.setId(6);
		edge.setIdGraph(1);
		edge.setSource("A");
		edge.setTarget("D");
		edges.add(edge);
		
		edge = new Edge();
		edge.setDistance(3);
		edge.setId(7);
		edge.setIdGraph(1);
		edge.setSource("C");
		edge.setTarget("E");
		edges.add(edge);
		
		edge = new Edge();
		edge.setDistance(3);
		edge.setId(8);
		edge.setIdGraph(1);
		edge.setSource("E");
		edge.setTarget("B");
		edges.add(edge);
		
		edge = new Edge();
		edge.setDistance(3);
		edge.setId(9);
		edge.setIdGraph(1);
		edge.setSource("A");
		edge.setTarget("E");
		edges.add(edge);
		
		return edges;
	}
}
