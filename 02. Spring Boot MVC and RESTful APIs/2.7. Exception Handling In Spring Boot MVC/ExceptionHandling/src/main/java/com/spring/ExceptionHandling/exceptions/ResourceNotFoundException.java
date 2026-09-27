package com.spring.ExceptionHandling.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
