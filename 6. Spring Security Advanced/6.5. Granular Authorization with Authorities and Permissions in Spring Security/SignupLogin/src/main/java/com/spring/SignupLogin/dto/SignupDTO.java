package com.spring.SignupLogin.dto;

import java.util.Set;

import com.spring.SignupLogin.enums.Role;

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
public class SignupDTO {
	
	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
	@Email(message = "Please enter valid email")
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
	private String password;
	
	private Set<Role> roles;
}
