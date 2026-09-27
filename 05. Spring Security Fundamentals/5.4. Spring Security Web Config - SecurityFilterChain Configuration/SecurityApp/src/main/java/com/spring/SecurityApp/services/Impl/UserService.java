package com.spring.SecurityApp.services.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.SecurityApp.exceptions.ResourceNotFoundException;
import com.spring.SecurityApp.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

// @Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User Not Found With Email: " + username));
	}
}
