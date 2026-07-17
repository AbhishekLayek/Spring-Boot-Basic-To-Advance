package com.spring.SecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.SecurityApp.entities.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long>{

}