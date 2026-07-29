package com.spring.SignupLogin.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.SignupLogin.dto.LoginDTO;
import com.spring.SignupLogin.dto.SignupDTO;
import com.spring.SignupLogin.dto.UserDTO;
import com.spring.SignupLogin.entities.UserEntity;
import com.spring.SignupLogin.exceptions.ResourceNotFoundException;
import com.spring.SignupLogin.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JWTService jwtService;

	public UserDTO signup(SignupDTO signupDTO) {
		if(userRepository.existsByEmail(signupDTO.getEmail())) throw new ResourceNotFoundException("User Already Exists With Email " + signupDTO.getEmail());
		
		UserEntity savedUserEntity = modelMapper.map(signupDTO, UserEntity.class);
		savedUserEntity.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
		
		return modelMapper.map(userRepository.save(savedUserEntity), UserDTO.class);
	}

	public String login(LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
				);
				
		UserEntity user = (UserEntity)authentication.getPrincipal();
		
		return jwtService.generateToken(user);
	}
}
