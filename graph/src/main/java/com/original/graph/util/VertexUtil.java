package com.original.graph.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.original.graph.model.Vertex;
import com.original.graph.model.jpa.Edge;

public class VertexUtil {

	/**
	 * Create List Vertex based List Edges received
	 * @param edges
	 * @return List<Vertex>
	 */
	public static List<Vertex> createVertex(List<Edge> edges) {

		Set<String> collectedSources = new HashSet<>();
		List<Vertex> vertices = new ArrayList<>();
		Vertex vertex;

		for (Edge edge : edges) {
			collectedSources.add(edge.getSource());
			collectedSources.add(edge.getTarget());
		}

		Iterator<String> sourcesIt = collectedSources.iterator();
		List<Edge> collectedEdges;
		while (sourcesIt.hasNext()) {
			String source = sourcesIt.next();
			vertex = new Vertex(source);
			collectedEdges = edges.stream().filter(item -> item.getSource().equals(source))
					.collect(Collectors.toList());
			vertex.setOut(collectedEdges);
			collectedEdges = edges.stream().filter(item -> item.getTarget().equals(source))
					.collect(Collectors.toList());
			vertex.setIn(collectedEdges);
			vertices.add(vertex);
		}

		return vertices;
	}
	
	/**
	 * Return Source Vertex from String source received
	 * @param source
	 * @param vertices
	 * @return Vertex (source)
	 */
	public static Vertex getVertex(String source, List<Vertex> vertices) {
		for(Vertex vertex : vertices){
			if(vertex.getSource().equals(source)) {
				return vertex;
			}
		}
		return null;
	}
	
	/**
	 * Return Target Vertex from String target received
	 * @param target
	 * @param vertexList
	 * @return Vertex(target)
	 */
	public static Vertex getTarget(String target, List<Vertex> vertexList) {
		return getVertex(target, vertexList);
	}

}
