package com.spring.APIResponse.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
