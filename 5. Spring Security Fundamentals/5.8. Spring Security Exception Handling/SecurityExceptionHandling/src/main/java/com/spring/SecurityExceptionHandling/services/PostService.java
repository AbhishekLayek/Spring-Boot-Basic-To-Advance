package com.spring.SecurityExceptionHandling.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.spring.SecurityExceptionHandling.dto.PostDTO;
import com.spring.SecurityExceptionHandling.entities.PostEntity;
import com.spring.SecurityExceptionHandling.exceptions.ResourceNotFoundException;
import com.spring.SecurityExceptionHandling.repositories.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	
	private void validatePostExistance(Long id) {
		if(! postRepository.existsById(id)) throw new ResourceNotFoundException("Post Not Found With Id " + id);
	}
	
	public PostDTO createPost(PostDTO postDTO) {
		PostEntity postEntity = postRepository.save(modelMapper.map(postDTO, PostEntity.class));
		return modelMapper.map(postEntity, PostDTO.class);
	}

	public Optional<PostDTO> getPostById(Long id) {
		return postRepository.findById(id).map(postEntity -> modelMapper.map(postEntity, PostDTO.class));
	}

	public List<PostDTO> getAllPosts() {
		return postRepository.findAll().stream().map(postEntity -> modelMapper.map(postEntity, PostDTO.class)).collect(Collectors.toList());
	}

	public PostDTO updatePost(Long id, PostDTO postDTO) {
		validatePostExistance(id);
		PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);
		postEntity.setId(id);
		return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
	}

	public PostDTO updatePostPartially(Long id, Map<String, Object> updates) {
		validatePostExistance(id);
		PostEntity postEntity = postRepository.findById(id).get();
		updates.forEach((fields, values)->{
			Field fieldToBeUpdated = ReflectionUtils.getRequiredField(PostEntity.class, fields);
			fieldToBeUpdated.setAccessible(true);
			ReflectionUtils.setField(fieldToBeUpdated, postEntity, values);
		});
		return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
	}
}