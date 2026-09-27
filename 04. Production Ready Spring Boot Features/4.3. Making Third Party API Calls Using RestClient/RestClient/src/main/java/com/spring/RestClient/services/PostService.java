package com.spring.RestClient.services;

import java.util.List;
import java.util.Optional;
import com.spring.RestClient.dto.PostDTO;

public interface PostService {

	PostDTO createPost(PostDTO postDTO);

	Optional<PostDTO> getPostById(Long id);

	List<PostDTO> getAllPosts();

}
