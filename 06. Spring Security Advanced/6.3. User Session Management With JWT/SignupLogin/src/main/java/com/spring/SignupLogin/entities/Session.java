package com.spring.SignupLogin.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long session_id;
	
	private String refreshToken;
	
	@ManyToOne
	private UserEntity user;
	
	@CreationTimestamp
	private LocalDateTime lastUsedAt;
}