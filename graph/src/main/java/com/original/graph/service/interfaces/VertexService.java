package com.original.graph.service.interfaces;

import java.util.List;
import com.original.graph.model.Vertex;
import com.original.graph.model.jpa.Edge;

public interface VertexService {
	List<Vertex> createVertex(List<Edge> edges);
}
