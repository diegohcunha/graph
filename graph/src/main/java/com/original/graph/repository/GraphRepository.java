package com.original.graph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.original.graph.model.jpa.Graph;

@Repository
public interface GraphRepository extends JpaRepository<Graph, Integer>{

	@Query("SELECT graph FROM Graph graph WHERE graph.id = ?1")
	Graph find(Integer id);

}
