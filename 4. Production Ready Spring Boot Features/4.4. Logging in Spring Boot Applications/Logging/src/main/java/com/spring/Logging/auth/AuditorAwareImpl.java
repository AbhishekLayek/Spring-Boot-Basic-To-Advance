package com.spring.Logging.auth;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String>{
	@Override
	public Optional<String> getCurrentAuditor() {
		// 1. get the security context
		// 2. get the authentication
		// 3. get the principal
		// 4. get the user
		return Optional.of("Abhishek Layek");
	}

}
