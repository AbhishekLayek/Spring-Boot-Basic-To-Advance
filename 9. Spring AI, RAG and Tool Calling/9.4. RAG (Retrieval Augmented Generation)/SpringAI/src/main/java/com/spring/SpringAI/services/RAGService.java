package com.spring.SpringAI.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RAGService {
	private final ChatClient chatClient;
	private final VectorStore vectorStore;
	
	@Value("classpath:Spring_AI.pdf")
	Resource pdfFile;
	
	public void ingestPdfDataToVectorStore() {
		PagePdfDocumentReader reader = new PagePdfDocumentReader(pdfFile);
		List<Document> pages = reader.read();
		
		TokenTextSplitter tokenTextSplitter = TokenTextSplitter
				.builder()
				.withChunkSize(200)
				.build();
		
		List<Document> chunks = tokenTextSplitter.apply(pages);
		vectorStore.add(chunks);
	}
	
	public String askAI(String prompt) {
		String template = """
				You are an AI assistant called AIBuddy
                
                Rules:
                - Use ONLY the information provided in the context
                - You MAY rephrase, summarize, and explain in natural language
                - Do NOT introduce new concepts or facts
                - If multiple context sections are relevant, combine them into a single explanation.
                - If the answer is not present, say "I don't know"
                
                Context:
                {context}
                
                Answer in a friendly, conversational tone.
				""";
		
		List<Document> documents = vectorStore.similaritySearch(SearchRequest
				.builder()
				.query(prompt)
				.topK(2)
				.build()
				);
		
		String context = documents
				.stream()
				.map(Document::getText)
				.collect(Collectors.joining("\n\n"));
		
		PromptTemplate promptTemplate = new PromptTemplate(template);
		String systemPrompt = promptTemplate.render(Map.of("context", context));
		
		return chatClient
				.prompt()
				.system(systemPrompt)
				.user(prompt)
				.call()
				.content();
	}
}
