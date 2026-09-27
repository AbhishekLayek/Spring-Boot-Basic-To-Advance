package com.spring.APIResponse.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig extends ModelMapper{
	
	@Bean
	ModelMapper getModelMapperObject() {
		return new ModelMapper();
	}
}
