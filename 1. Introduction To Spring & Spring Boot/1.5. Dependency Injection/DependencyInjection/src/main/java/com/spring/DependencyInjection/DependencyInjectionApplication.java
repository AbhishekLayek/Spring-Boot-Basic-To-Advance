package com.spring.DependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.DependencyInjection.ConstructorInjection.NotificationService;
import com.spring.DependencyInjection.FieldInjection.PaymentService;
import com.spring.DependencyInjection.SetterInjection.Netflix;

@SpringBootApplication
public class DependencyInjectionApplication implements CommandLineRunner{
	
	@Autowired
	PaymentService paymentService; // Field Injection
	
	private final NotificationService notificationService;
	
	// Constructor Injection
	public DependencyInjectionApplication(@Qualifier("email") NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	private Netflix netflix;
	
	// Setter Injection
	@Autowired
	public void setNetflix(Netflix netflix) {
		this.netflix = netflix;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DependencyInjectionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		paymentService.pay();
		notificationService.send("Welcome To Spring Boot");
		netflix.watch();
	}

}
