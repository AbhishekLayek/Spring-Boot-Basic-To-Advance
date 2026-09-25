package com.spring.SpringAI.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class WeatherTool {
	
	@Tool(description = "Get the weather of a city")
	public String getWeather(@ToolParam(description = "City name for which to get the weather information", required = true) String city) {
		return switch (city) {
		case "Delhi" -> "Sunny, 30 Degree Celsius";
		case "Kolkata" -> "Sunny, 35 Degree Celsius";
		case "London" -> "Cloudy, 5 Degree Celsius";
		default -> "Can't Identify The City";
		};
	}
}