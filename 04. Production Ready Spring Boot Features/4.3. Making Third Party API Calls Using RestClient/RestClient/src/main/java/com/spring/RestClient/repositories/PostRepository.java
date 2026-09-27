package com.spring.RestClient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.RestClient.entities.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long>{

}
