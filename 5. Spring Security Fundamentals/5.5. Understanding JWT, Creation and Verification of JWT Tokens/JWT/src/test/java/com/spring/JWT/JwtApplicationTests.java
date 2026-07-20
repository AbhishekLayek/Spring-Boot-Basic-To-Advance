package com.spring.JWT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.JWT.entities.User;
import com.spring.JWT.services.Impl.JWTService;

@SpringBootTest
class JwtApplicationTests {
	
	@Autowired
	private JWTService jwtService;

	@Test
	void testJWTService() {
		User user = User
				.builder()
				.id(1L)
				.email("abhisheklayek@gmail.com")
				.password("Abhishek@1234")
				.build();
		
		String token = jwtService.generateToken(user);
		System.out.println(token);
		
		Long id = jwtService.getUserIdFromToken(token);
		System.out.println("User Id: " + id);
	}

}
