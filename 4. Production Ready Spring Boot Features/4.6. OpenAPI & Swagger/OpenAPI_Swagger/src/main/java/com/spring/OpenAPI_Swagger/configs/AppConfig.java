package com.spring.OpenAPI_Swagger.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.spring.OpenAPI_Swagger.auth.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditrAwareObject")
public class AppConfig {
	
	@Bean
	ModelMapper getModelMapperObject() {
		return new ModelMapper();
	}
	
	@Bean
	AuditorAware<String> getAuditrAwareObject(){
		return new AuditorAwareImpl();
	}
}
