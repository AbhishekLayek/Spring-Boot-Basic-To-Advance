package com.spring.APIResponse.advices;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ApiResponse<T> {
	
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private T data;
	private ApiError error;
	
	public ApiResponse() {
		this.timestamp = LocalDateTime.now();
	}

	public ApiResponse(T data) {
		this();
		this.data = data;
	}

	public ApiResponse(ApiError error) {
		this();
		this.error = error;
	}
}
