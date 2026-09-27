package com.spring.SpringMVC.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig extends ModelMapper{
	
	@Bean
	ModelMapper getModelMapperObj() {
		return new ModelMapper();
	}
}
