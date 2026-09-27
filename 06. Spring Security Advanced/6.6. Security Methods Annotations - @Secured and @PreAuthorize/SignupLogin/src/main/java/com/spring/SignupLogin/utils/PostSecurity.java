package com.spring.SignupLogin.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.SignupLogin.dto.PostDTO;
import com.spring.SignupLogin.entities.UserEntity;
import com.spring.SignupLogin.services.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostSecurity {
	
	private final PostService postService;
	
	public boolean isOwnerOfPost(Long postId) {
		UserEntity user = (UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PostDTO post = postService.getPostById(postId).get();
		return post.getAuthor().getId().equals(user.getId());
	}
}
