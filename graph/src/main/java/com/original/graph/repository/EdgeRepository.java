package com.original.graph.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.original.graph.model.jpa.Edge;

@Repository
public interface EdgeRepository extends JpaRepository<Edge, Integer>{

	@Query("SELECT edge FROM Edge edge WHERE edge.idGraph = ?1")
	List<Edge> loadByGraph(Integer idGraph);

}
