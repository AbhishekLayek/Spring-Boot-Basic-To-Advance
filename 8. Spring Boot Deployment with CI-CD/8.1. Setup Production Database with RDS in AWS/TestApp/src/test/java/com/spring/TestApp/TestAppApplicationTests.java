package com.spring.TestApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.spring.TestApp.configs.TestContainerConfig;

@SpringBootTest
@Import(TestContainerConfig.class)
class TestAppApplicationTests {

	@Test
	void contextLoads() {
	}

}
