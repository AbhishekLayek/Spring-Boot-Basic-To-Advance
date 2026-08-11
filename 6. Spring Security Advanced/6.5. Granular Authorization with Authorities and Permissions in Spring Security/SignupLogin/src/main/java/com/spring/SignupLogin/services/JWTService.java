package com.spring.SignupLogin.services;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring.SignupLogin.entities.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	@Value("${jwt.secretkey}")
	private String secretKey;
	
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateAccessToken(UserEntity user) {
		return Jwts
			.builder()
			.subject(user.getId().toString())
			.claim("email", user.getEmail())
			.claim("roles", Set.of("ADMIN", "USER"))
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + 1000*60*10)) // Valid For 10 mins
			.signWith(getSecretKey())
			.compact();
	}
	
	public String generateRefreshToken(UserEntity user) {
		return Jwts
			.builder()
			.subject(user.getId().toString())
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + 1000L *60*60*24*30*6)) // Valid For 6 Months
			.signWith(getSecretKey())
			.compact();
	}
	
	public Long getUserIdFromToken(String token) {
		Claims claim = Jwts
				.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
		return Long.valueOf(claim.getSubject());
	}
}
