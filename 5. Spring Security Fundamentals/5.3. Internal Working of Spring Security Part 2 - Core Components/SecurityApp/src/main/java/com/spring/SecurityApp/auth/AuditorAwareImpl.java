package com.spring.SecurityApp.auth;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String>{
	
	// 1. Get the security context
	// 2. Get the authentication
	// 3. Get the principal
	// 4. Get the user
	
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Abhishek Layek");
	}
	
}
