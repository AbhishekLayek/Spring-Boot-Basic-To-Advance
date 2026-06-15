package com.spring.SpringMVC_ServiceLayer.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig extends ModelMapper{
	
	@Bean
	ModelMapper getmodelMapper() {
		return new ModelMapper();
	}
}
