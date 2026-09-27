package com.spring.SecurityExceptionHandling.advices;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiError {
	private HttpStatus status;
	private String message;
	private List<String> subErrors;
}