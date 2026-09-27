package com.spring.SpringAI.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RAGServiceTest {
	
	@Autowired
	private RAGService ragService;

	@Test
	void testIngestPdfDataToVectorStore() {
		ragService.ingestPdfDataToVectorStore();
	}
	
	@Test
	void testAskAI() {
		var response = ragService.askAI("Explain RAG.");
		System.out.println(response);
	}
}