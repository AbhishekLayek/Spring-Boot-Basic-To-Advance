package com.spring.SecurityApp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SecurityApp.dto.PostDTO;
import com.spring.SecurityApp.exceptions.ResourceNotFoundException;
import com.spring.SecurityApp.services.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;
	
	@PostMapping("/add")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
		return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/find")
	public ResponseEntity<PostDTO> getPostById(@RequestParam Long id){
		return postService.getPostById(id).map(postDTO -> ResponseEntity.ok(postDTO)).orElseThrow(() -> new ResourceNotFoundException("Post Not Found With Id: " + id));
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		return ResponseEntity.ok(postService.getAllPosts());
	}
	
	@PutMapping("/update")
	public ResponseEntity<PostDTO> updatePost(@RequestParam Long id, @RequestBody PostDTO postDTO){
		return ResponseEntity.ok(postService.updatePost(id, postDTO));
	}
	
	@PatchMapping("/updatePartially")
	public ResponseEntity<PostDTO> updatePostPartially(@RequestParam Long id, @RequestBody Map<String, Object> updates){
		return ResponseEntity.ok(postService.updatePostPartially(id, updates));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Boolean> deletePostById(@RequestParam Long id){
		return ResponseEntity.ok(postService.deletePostById(id));
	}
}
