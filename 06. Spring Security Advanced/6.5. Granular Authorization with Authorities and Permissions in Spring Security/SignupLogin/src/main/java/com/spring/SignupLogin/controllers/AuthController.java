package com.spring.SignupLogin.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SignupLogin.dto.LoginDTO;
import com.spring.SignupLogin.dto.LoginResponse;
import com.spring.SignupLogin.dto.SignupDTO;
import com.spring.SignupLogin.dto.UserDTO;
import com.spring.SignupLogin.services.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@Value("${deploy.env}")
	private String deployEnv;
	
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> signup(@RequestBody SignupDTO signupDTO){
		return new ResponseEntity<>(authService.signup(signupDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response){
		LoginResponse loginResponse = authService.login(loginDTO);
		
		Cookie cookie = new Cookie("refreshToken", loginResponse.getRefreshToken());
		cookie.setHttpOnly(true);
		cookie.setSecure(deployEnv.equals("production"));
		response.addCookie(cookie);
		
		return ResponseEntity.ok(loginResponse);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<LoginResponse> refresh(HttpServletRequest request){
		String refreshToken = Arrays.stream(request.getCookies())
		.filter(cookie -> cookie.getName().equals("refreshToken"))
		.findFirst()
		.map(Cookie::getValue)
		.orElseThrow(() -> new AuthenticationServiceException("Refresh Token Not Found"));
		
		LoginResponse loginResponse = authService.refresh(refreshToken);
		
		return ResponseEntity.ok(loginResponse);
	}
}
