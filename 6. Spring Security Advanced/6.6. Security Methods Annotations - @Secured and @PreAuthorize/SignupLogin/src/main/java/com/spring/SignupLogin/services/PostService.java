package com.spring.SignupLogin.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.SignupLogin.dto.PostDTO;
import com.spring.SignupLogin.entities.PostEntity;
import com.spring.SignupLogin.entities.UserEntity;
import com.spring.SignupLogin.exceptions.ResourceNotFoundException;
import com.spring.SignupLogin.repositories.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	
	private void validatePostExistance(Long id) {
		if(! postRepository.existsById(id)) throw new ResourceNotFoundException("Post Not Found With Id: " + id);
	}
	
	public PostDTO createPost(PostDTO postDTO) {
		UserEntity user = (UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);
		postEntity.setAuthor(user);
		return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
	}

	public Optional<PostDTO> getPostById(Long id) {
		return postRepository.findById(id).map(postEntity -> modelMapper.map(postEntity, PostDTO.class));
	}

	public List<PostDTO> getAllPosts() {
		return postRepository.findAll().stream().map(PostEntity -> modelMapper.map(PostEntity, PostDTO.class)).collect(Collectors.toList());
	}

	public PostDTO updatePost(Long id, PostDTO postDTO) {
		validatePostExistance(id);
		PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);
		postEntity.setId(id);
		return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
	}

	public PostDTO updatePostsPartially(Long id, Map<String, Object> updates) {
		validatePostExistance(id);
		PostEntity postEntity = postRepository.findById(id).get();
		updates.forEach((field, value)->{
			Field fieldToBeUpdate = ReflectionUtils.getRequiredField(PostEntity.class, field);
			fieldToBeUpdate.setAccessible(true);
			ReflectionUtils.setField(fieldToBeUpdate, postEntity, value);
		});
		return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
	}

	public Boolean deletePostById(Long id) {
		validatePostExistance(id);
		postRepository.deleteById(id);
		return true;
	}
}
