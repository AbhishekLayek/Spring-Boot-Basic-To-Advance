package com.spring.Auditing.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.spring.Auditing.configs.ModelMapperConfig;
import com.spring.Auditing.dto.PostDTO;
import com.spring.Auditing.entities.PostEntity;
import com.spring.Auditing.repositories.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	private final PostRepository postRepository;
	private final ModelMapperConfig modelMapper;

	@Override
	public List<PostDTO> getAllPosts() {
		return postRepository.findAll().stream().map(PostEntity -> modelMapper.map(PostEntity, PostDTO.class)).collect(Collectors.toList());
	}

	@Override
	public Optional<PostDTO> getPostById(Long id) {
		return postRepository.findById(id).map(postEntity -> modelMapper.map(postEntity, PostDTO.class));
	}

	@Override
	public PostDTO createPost(PostDTO postDTO) {
		PostEntity postEntity = postRepository.save(modelMapper.map(postDTO, PostEntity.class));
		return modelMapper.map(postEntity, PostDTO.class);
	}
}
