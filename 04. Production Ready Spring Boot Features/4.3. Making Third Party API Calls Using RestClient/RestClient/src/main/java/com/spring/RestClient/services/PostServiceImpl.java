package com.spring.RestClient.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.spring.RestClient.dto.PostDTO;
import com.spring.RestClient.entities.PostEntity;
import com.spring.RestClient.repositories.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;

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
}
