package com.spring.SignupLogin.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.spring.SignupLogin.auth.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")
public class AppConfig {
	@Bean
	ModelMapper getModelMapperObject() {
		return new ModelMapper();
	}
	
	@Bean
	AuditorAware<String> getAuditorAwareImpl(){
		return new AuditorAwareImpl();
	}
}
