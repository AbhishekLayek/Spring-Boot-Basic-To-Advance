package com.spring.SignupLogin.handlers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.spring.SignupLogin.entities.UserEntity;
import com.spring.SignupLogin.services.JWTService;
import com.spring.SignupLogin.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Value("${deploy.env}")
	private String deployEnv;
	
	private final UserService userService;
	private final JWTService jwtService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
		DefaultOAuth2User oauth2User = (DefaultOAuth2User)token.getPrincipal();
		
		String email = oauth2User.getAttribute("email");
		
		UserEntity user = userService.getUserByEmail(email);
		
		if(user == null) {
			UserEntity newUser = UserEntity
					.builder()
					.name(oauth2User.getAttribute("name"))
					.email(email)
					.build();
			
			user = userService.save(newUser);
		}
		
		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		
		Cookie cookie = new Cookie("refreshToken", refreshToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(deployEnv.equals("production"));
		response.addCookie(cookie);
		
		String frontendUrl = "http://localhost:9000/home.html?token=" + accessToken;
		
		response.sendRedirect(frontendUrl);
		
	}
}
