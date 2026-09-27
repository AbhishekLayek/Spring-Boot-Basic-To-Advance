package com.spring.SpringAI.services;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIService {
	private final ChatClient chatClient;
	
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
