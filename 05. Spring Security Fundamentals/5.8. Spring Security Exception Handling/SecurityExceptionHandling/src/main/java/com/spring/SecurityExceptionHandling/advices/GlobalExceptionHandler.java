package com.spring.SecurityExceptionHandling.advices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.SecurityExceptionHandling.exceptions.ResourceNotFoundException;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice(basePackages = "com.spring.SecurityExceptionHandling.controllers")
public class GlobalExceptionHandler {
	
	private ResponseEntity<ApiResponse<?>> buildApiErrorResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException exception){
		ApiError apiError = ApiError
				.builder()
				.status(HttpStatus.NOT_FOUND)
				.message(exception.getLocalizedMessage())
				.build();
		
		return buildApiErrorResponseEntity(apiError);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){
		ApiError apiError = ApiError
				.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.message(exception.getLocalizedMessage())
				.build();
		
		return buildApiErrorResponseEntity(apiError);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
		
		List<String> subErrors = exception
				.getBindingResult()
				.getAllErrors()
				.stream()
				.map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());
		
		ApiError apiError = ApiError
				.builder()
				.status(HttpStatus.BAD_REQUEST)
				.message(exception.getLocalizedMessage())
				.subErrors(subErrors)
				.build();
		
		return buildApiErrorResponseEntity(apiError);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException exception){
		ApiError apiError = ApiError
				.builder()
				.status(HttpStatus.UNAUTHORIZED)
				.message(exception.getLocalizedMessage())
				.build();
		
		return buildApiErrorResponseEntity(apiError);
	}
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException exception){
		ApiError apiError = ApiError
				.builder()
				.status(HttpStatus.UNAUTHORIZED)
				.message(exception.getLocalizedMessage())
				.build();
		
		return buildApiErrorResponseEntity(apiError);
	}
}