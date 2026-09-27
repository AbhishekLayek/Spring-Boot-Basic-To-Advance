package com.spring.Auditing.services;

import java.util.List;
import java.util.Optional;

import com.spring.Auditing.dto.PostDTO;

public interface PostService {
	List<PostDTO> getAllPosts();
	Optional<PostDTO> getPostById(Long id);
	PostDTO createPost(PostDTO postDTO);
}
