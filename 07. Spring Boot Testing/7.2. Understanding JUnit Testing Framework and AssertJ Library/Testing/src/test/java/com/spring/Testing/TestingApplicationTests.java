package com.spring.Testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestingApplicationTests {
	
	@BeforeEach
	void setUp() {
		log.info("Starting the method, setting up config...");
	}
	
	@AfterEach
	void tearDown() {
		log.info("Tearing down the method...");
	}
	
	@BeforeAll
	static void setUpOnce() {
		log.info("Setup once...");
	}
	
	@AfterAll
	static void tearDownOnce() {
		log.info("Tearing down all...");
	}

	@Test
	@Disabled
	void contextLoads() {
	}
	
	@Test
	void testAddTwoNumbers() {
		int a = 5;
		int b = 10;
		
		int result = addTwoNumbers(a, b);
		
		assertThat(result).isEqualTo(15);
	}
	
	@Test
	void testDivideTwoNumbers_whenDenominatorIsZero_thenArithmeticException() {
		int a = 5;
		int b = 0;
		
		assertThatThrownBy(() -> divideTwoNumbers(a, b))
			.isInstanceOf(ArithmeticException.class)
			.hasMessage("/ by zero");
	}
	
	int addTwoNumbers(int a, int b) {
		return a+b;
	}
	
	double divideTwoNumbers(int a, int b) {
		try {
			return a/b;
		}
		catch(ArithmeticException e) {
			log.info("Arithmetic Exception Occurred" + e.getLocalizedMessage());
			throw new ArithmeticException(e.getLocalizedMessage());
		}
	}
}
