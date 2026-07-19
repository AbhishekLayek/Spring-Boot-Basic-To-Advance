package com.spring.JWT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.JWT.entities.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long>{

}