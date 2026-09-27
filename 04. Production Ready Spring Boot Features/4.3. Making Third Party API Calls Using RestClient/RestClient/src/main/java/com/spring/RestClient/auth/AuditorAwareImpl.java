package com.spring.RestClient.auth;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String>{
	
	// 1. get the security context
	// 2. get the authentication
	// 3. get the principle
	// 4. get the user
	
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Abhishek Layek");
	}

}
