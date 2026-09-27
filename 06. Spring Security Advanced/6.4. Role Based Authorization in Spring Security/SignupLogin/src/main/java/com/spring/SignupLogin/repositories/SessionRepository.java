package com.spring.SignupLogin.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.SignupLogin.entities.Session;
import com.spring.SignupLogin.entities.UserEntity;

public interface SessionRepository extends JpaRepository<Session, Long>{
	List<Session> findByUser(UserEntity user);
	Optional<Session> findByRefreshToken(String refreshToken);
}