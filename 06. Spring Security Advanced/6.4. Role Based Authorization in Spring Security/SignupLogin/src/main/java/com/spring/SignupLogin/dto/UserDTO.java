package com.spring.SignupLogin.dto;

import java.util.Set;

import com.spring.SignupLogin.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;
	private String name;
	private String email;
	private Set<Role> roles;
}
