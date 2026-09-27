package com.spring.SecurityExceptionHandling.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SecurityExceptionHandling.dto.LoginDTO;
import com.spring.SecurityExceptionHandling.dto.LoginResponse;
import com.spring.SecurityExceptionHandling.dto.SignupDTO;
import com.spring.SecurityExceptionHandling.dto.UserDTO;
import com.spring.SecurityExceptionHandling.services.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	
	@PostMapping("/signup") 
	public ResponseEntity<UserDTO> signup(@RequestBody SignupDTO signupDTO){
		return new ResponseEntity<>(authService.signup(signupDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response){
		String token = authService.login(loginDTO);
		
		Cookie cookie = new Cookie("jwttoken", token);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		
		return ResponseEntity.ok(new LoginResponse(token));
	}
}
