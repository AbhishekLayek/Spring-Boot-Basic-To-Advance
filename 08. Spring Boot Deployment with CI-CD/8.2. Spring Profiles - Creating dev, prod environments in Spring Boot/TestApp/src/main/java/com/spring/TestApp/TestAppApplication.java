package com.spring.TestApp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.TestApp.services.DataService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class TestAppApplication implements CommandLineRunner{
	
	private final DataService dataService;
	
	@Value("${my-variable}")
	private String myVariable;

	public static void main(String[] args) {
		SpringApplication.run(TestAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("The Data Is: " + dataService.getData());
		System.out.println("My Variable: " + myVariable);
	}
}