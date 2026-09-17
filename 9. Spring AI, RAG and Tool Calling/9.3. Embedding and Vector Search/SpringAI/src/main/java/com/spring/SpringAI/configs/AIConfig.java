package com.spring.SpringAI.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {
	
	@Bean
	ChatClient getChatClientObject(ChatClient.Builder builder) {
		return builder.build();
	}
}
