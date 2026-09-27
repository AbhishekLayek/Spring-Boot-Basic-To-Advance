package com.spring.SpringAI.services;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIService {
	private final ChatClient chatClient;
	private final EmbeddingModel embeddingModel;
	private final VectorStore vectorStore;
	
	public float[] getEmbedding(String text) {
		return embeddingModel.embed(text);
	}
	
	public void ingestDataToVectorStore() {
		List<Document> movies = List.of(

			    new Document(
			        "Inception is a science fiction thriller about dreams and dream manipulation.",
			        Map.of(
			            "title", "Inception",
			            "director", "Christopher Nolan",
			            "genre", "Sci-Fi",
			            "year", 2010
			        )
			    ),

			    new Document(
			        "The Dark Knight is an action crime thriller featuring Batman and the Joker.",
			        Map.of(
			            "title", "The Dark Knight",
			            "director", "Christopher Nolan",
			            "genre", "Action",
			            "year", 2008
			        )
			    ),

			    new Document(
			        "The Hangover is a comedy about three friends dealing with the consequences of a wild night in Las Vegas.",
			        Map.of(
			            "title", "The Hangover",
			            "director", "Todd Phillips",
			            "genre", "Comedy",
			            "year", 2009
			        )
			    )
			);
		
		vectorStore.add(movies);
	}
	
	public List<Document> similaritySearch(String text){
		return vectorStore.similaritySearch(SearchRequest
				.builder()
				.query(text)
				.topK(1)
				.build()
				);
	}
	
	public String getInfo(String topic) {
		
		String systemPrompt = """
				You need to explain the topic in 2 lines.
				Explain the topic: {topic}
				""";
		
		PromptTemplate promptTemplate = new PromptTemplate(systemPrompt);
		String renderedText = promptTemplate.render(Map.of("topic", topic));
		
		var response = chatClient
			.prompt()
			.user(renderedText)
			.call()
			.chatClientResponse();
		
		return response.chatResponse().getResult().getOutput().getText();
	}
}
