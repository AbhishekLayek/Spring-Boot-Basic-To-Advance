package com.spring.SpringAI.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AIServiceTest {
	
	@Autowired
	private AIService aiService;
	
	@Test
	void testGetInfo() {
		String text = aiService.getInfo("Spring AI in 100 words.");
		System.out.println(text);
	}

}