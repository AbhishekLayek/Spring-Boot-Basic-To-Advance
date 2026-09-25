package com.spring.SpringAI.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {
	@Bean
	ChatClient getChatClientObject(ChatClient.Builder builder) {
		return builder
				.defaultAdvisors(new SimpleLoggerAdvisor())
				.build();
	}
	
	@Bean
	ChatMemory getChatMemoryObject(JdbcChatMemoryRepository chatMemoryRepository) {
		return MessageWindowChatMemory
				.builder()
				.chatMemoryRepository(chatMemoryRepository)
				.maxMessages(10)
				.build();
	}
}