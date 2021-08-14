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

}
