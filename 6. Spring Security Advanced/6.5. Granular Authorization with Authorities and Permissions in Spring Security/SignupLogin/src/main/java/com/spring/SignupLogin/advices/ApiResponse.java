package com.spring.SignupLogin.advices;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;
	
	private T data;
	
	private ApiError apiError;

	public ApiResponse() {
		this.timestamp = LocalDateTime.now();
	}

	public ApiResponse(T data) {
		this();
		this.data = data;
	}

	public ApiResponse(ApiError apiError) {
		this();
		this.apiError = apiError;
	}
}
