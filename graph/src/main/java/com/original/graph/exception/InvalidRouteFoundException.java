package com.original.graph.exception;

public class InvalidRouteFoundException extends RuntimeException {
	private static final long serialVersionUID = -4077609944584294464L;

	public InvalidRouteFoundException(String msg) {
		super(msg);
	}
	public InvalidRouteFoundException() {
		super();
	}
}
