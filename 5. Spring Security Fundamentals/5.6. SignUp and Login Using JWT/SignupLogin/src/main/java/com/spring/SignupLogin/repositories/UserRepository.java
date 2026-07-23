package com.spring.SignupLogin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.SignupLogin.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	boolean existsByEmail(String email);
}
