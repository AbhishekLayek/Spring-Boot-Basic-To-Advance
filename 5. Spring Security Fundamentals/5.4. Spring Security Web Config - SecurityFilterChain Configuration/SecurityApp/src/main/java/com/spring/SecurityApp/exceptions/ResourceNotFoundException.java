package com.spring.SecurityApp.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
