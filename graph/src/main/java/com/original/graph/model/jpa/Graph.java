package com.original.graph.model.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.original.graph.model.Vertex;

@Entity
public class Graph implements Serializable{
	private static final long serialVersionUID = -6604943182160623366L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Transient
	private List<Vertex> vertices;
	
	@Transient
	private List<Edge> data;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Edge> getData() {
		return data;
	}
	public void setData(List<Edge> data) {
		this.data = data;
	}
}
