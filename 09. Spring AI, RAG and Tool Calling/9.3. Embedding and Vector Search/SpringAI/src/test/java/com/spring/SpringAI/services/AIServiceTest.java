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
	
	@Test
	void testGetEmbedding() {
		var embed = aiService.getEmbedding("Welcome to Spring AI");
		System.out.println(embed.length);
	}
	
	@Test
	void testIngestDataToVectorStore() {
		aiService.ingestDataToVectorStore();
	}
	
	@Test
	void testSimilaritySearch() {
		var response = aiService.similaritySearch("science fiction movie");
		System.out.println(response);
	}
}