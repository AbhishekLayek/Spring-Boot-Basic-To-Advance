package com.spring.SignupLogin.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SignupLogin.dto.PostDTO;
import com.spring.SignupLogin.exceptions.ResourceNotFoundException;
import com.spring.SignupLogin.services.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;
	
	@PostMapping("/add")
	@Secured({"ROLE_CREATOR","ROLE_ADMIN"})
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
		return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/find")
	@Secured({"ROLE_USER","ROLE_CREATOR","ROLE_ADMIN"})
	public ResponseEntity<PostDTO> getPostById(@RequestParam Long id){
		return postService.getPostById(id).map(postDTO -> ResponseEntity.ok(postDTO)).orElseThrow(() -> new ResourceNotFoundException("Post Not Found With Id: " + id));
	}
	
	@GetMapping("/findAll")
	@PreAuthorize("hasAnyRole('USER','CREATOR','ADMIN')")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		return ResponseEntity.ok(postService.getAllPosts());
	}
	
	@PutMapping("/update")
	@PreAuthorize("@postSecurity.isOwnerOfPost(#id)")
	public ResponseEntity<PostDTO> updatePost(@RequestParam Long id, @RequestBody PostDTO postDTO){
		return ResponseEntity.ok(postService.updatePost(id, postDTO));
	}
	
	@PatchMapping("/patchUpdate")
	@PreAuthorize("@postSecurity.isOwnerOfPost(#id)")
	public ResponseEntity<PostDTO> updatePostsPartially(@RequestParam Long id, @RequestBody Map<String, Object> updates){
		return ResponseEntity.ok(postService.updatePostsPartially(id, updates));
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasAnyRole('ADMIN') AND hasAnyAuthority('POST_DELETE')")
	public ResponseEntity<Boolean> deletePostById(@RequestParam Long id){
		return new ResponseEntity<>(postService.deletePostById(id), HttpStatus.NO_CONTENT);
	}
}
