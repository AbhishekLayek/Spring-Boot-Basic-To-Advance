package com.spring.Beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration annotation is used to indicate that a class serves as a source of bean definitions.

@Configuration
public class BeanConfig {
	
	// @Bean annotation is a method-level annotation used to explicitly declare a spring managed bean.
	
	@Bean
	RazorpayPaymentService razorpayPaymentService() {
		
		return new RazorpayPaymentService();
	}
}
