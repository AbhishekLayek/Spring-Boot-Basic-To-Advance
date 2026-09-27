package com.spring.DependencyInjection.SetterInjection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "content.type", havingValue = "webseries")
public class WebSeries implements Netflix{

	@Override
	public void watch() {
		System.out.println("Watching Webseries");
	}
}
