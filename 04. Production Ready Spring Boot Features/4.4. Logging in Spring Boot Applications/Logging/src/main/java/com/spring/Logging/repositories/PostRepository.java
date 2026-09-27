package com.spring.Logging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.Logging.entities.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long>{

}