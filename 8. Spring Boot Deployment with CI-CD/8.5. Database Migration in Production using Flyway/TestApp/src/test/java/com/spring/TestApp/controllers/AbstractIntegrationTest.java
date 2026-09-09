package com.spring.TestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.spring.TestApp.configs.TestContainerConfig;

@AutoConfigureWebTestClient(timeout = "100000")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfig.class)
public class AbstractIntegrationTest {
	
	@Autowired
	WebTestClient webTestClient;
}
