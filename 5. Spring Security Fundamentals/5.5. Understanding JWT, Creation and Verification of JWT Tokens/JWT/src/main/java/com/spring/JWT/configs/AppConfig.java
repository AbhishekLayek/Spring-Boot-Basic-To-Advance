package com.spring.JWT.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.spring.JWT.auth.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareObject")
public class AppConfig {
	
	@Bean
	ModelMapper getModelMapperObject() {
		return new ModelMapper();
	}
	
	@Bean
	AuditorAware<String> getAuditorAwareObject(){
		return new AuditorAwareImpl();
	}
}
