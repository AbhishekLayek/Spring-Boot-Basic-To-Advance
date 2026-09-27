package com.spring.SecurityExceptionHandling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.SecurityExceptionHandling.entities.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long>{

}