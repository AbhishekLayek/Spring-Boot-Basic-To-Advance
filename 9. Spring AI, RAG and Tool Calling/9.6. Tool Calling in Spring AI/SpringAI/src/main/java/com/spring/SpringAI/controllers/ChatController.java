package com.spring.SpringAI.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spring.SpringAI.tools.WeatherTool;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatController {
	private final ChatClient chatClient;
	private final WeatherTool weatherTool;
	
	@PostMapping("/chat")
	public String chat(@RequestBody String message) {
		return chatClient
				.prompt()
				.user(message)
				.tools(weatherTool)
				.call()
				.content();
	}
}