package com.original.graph.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -4077609944584294464L;

	public ResourceNotFoundException(String msg) {
	    super(msg);
	  }
}
