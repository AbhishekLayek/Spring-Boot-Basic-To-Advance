package com.spring.SecurityExceptionHandling.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.spring.SecurityExceptionHandling.entities.UserEntity;
import com.spring.SecurityExceptionHandling.services.JWTService;
import com.spring.SecurityExceptionHandling.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter{
	
	private final JWTService jwtService;
	private final UserService userService;
	private final HandlerExceptionResolver handlerExceptionResolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			final String requestHeaderToken = request.getHeader("Authorization");
			
			if(requestHeaderToken == null || !requestHeaderToken.startsWith("Bearer")) {
				filterChain.doFilter(request, response);
				return;
			}
			
			String token = requestHeaderToken.split("Bearer ")[1]; // **NOTE - Space after Bearer is very important.
			
			Long userId = jwtService.getUserIdFromToken(token);
			
			if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserEntity user = userService.getUserById(userId);
				
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
				filterChain.doFilter(request, response);
			}
		}
		catch(Exception e) {
			handlerExceptionResolver.resolveException(request, response, null, e);
		}
	}

}
