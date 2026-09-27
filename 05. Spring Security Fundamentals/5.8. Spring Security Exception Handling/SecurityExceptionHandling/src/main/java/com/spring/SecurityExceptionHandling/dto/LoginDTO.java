package com.spring.SecurityExceptionHandling.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
	
	@NotBlank(message = "Email is required")
	@Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
	@Email(message = "Please enter valid email")
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
	private String password;
}
