package com.spring.SecurityExceptionHandling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.SecurityExceptionHandling.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByEmail(String email);
	boolean existsByEmail(String email);
}
