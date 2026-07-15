package com.spring.SecurityApp.services.Impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.spring.SecurityApp.dto.PostDTO;
import com.spring.SecurityApp.entities.PostEntity;
import com.spring.SecurityApp.exceptions.ResourceNotFoundException;
import com.spring.SecurityApp.repositories.PostRepository;
import com.spring.SecurityApp.services.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	
	private void validatePostExistance(Long id) {
		if(! postRepository.existsById(id)) throw new ResourceNotFoundException("Post Not Found With Id: " + id);
	}
	
	@Override
	public PostDTO createPost(PostDTO postDTO) {
		PostEntity postEntity = postRepository.save(modelMapper.map(postDTO, PostEntity.class));
		return modelMapper.map(postEntity, PostDTO.class);
	}

	@Override
	public Optional<PostDTO> getPostById(Long id) {
		return postRepository.findById(id).map(postEntity -> modelMapper.map(postEntity, PostDTO.class));
	}

	@Override
	public List<PostDTO> getAllPosts() {
		return postRepository.findAll().stream().map(postEntity -> modelMapper.map(postEntity, PostDTO.class)).collect(Collectors.toList());
	}

	@Override
	public PostDTO updatePost(Long id, PostDTO postDTO) {
		validatePostExistance(id);
		PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);
		postEntity.setId(id);
		return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
	}

	@Override
	public PostDTO updatePostPartially(Long id, Map<String, Object> updates) {
		validatePostExistance(id);
		PostEntity postEntity = postRepository.findById(id).get();
		
		updates.forEach((field, value) -> {
			Field fieldToBeUpdated = ReflectionUtils.getRequiredField(PostEntity.class, field);
			fieldToBeUpdated.setAccessible(true);
			ReflectionUtils.setField(fieldToBeUpdated, postEntity, value);
		});
		
		return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
	}

	@Override
	public Boolean deletePostById(Long id) {
		validatePostExistance(id);
		postRepository.deleteById(id);
		return true;
	}
}
