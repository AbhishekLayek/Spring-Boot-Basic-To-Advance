package com.spring.SignupLogin.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.SignupLogin.entities.UserEntity;
import com.spring.SignupLogin.exceptions.ResourceNotFoundException;
import com.spring.SignupLogin.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

	public UserEntity getUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found With Id " + userId));
	}
}
