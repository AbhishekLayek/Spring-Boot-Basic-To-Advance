package com.spring.SpringAI.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RAGServiceTest {
	
	@Autowired
	private RAGService ragService;
	
	/*@Test
	void testIngestPdfDataToDatasource() {
		ragService.ingestPdfDataToVectorStore();
	}*/
	
	@Test
	void testAskAIUsingAdvisors() {
		var response = ragService.askAIUsingAdvisors("Explain Spring AI.", "Abhi123");
		System.out.println(response);
	}
}