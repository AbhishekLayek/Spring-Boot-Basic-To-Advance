package com.spring.RestClient.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.spring.RestClient.auth.AuditorAwareImpl;

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
