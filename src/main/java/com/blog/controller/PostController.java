package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.service.PostServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/blog-api/posts")
public class PostController {
	
	@Autowired
	private PostServiceImpl service;
	
	@PostMapping("/create")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto dto, 
												@RequestParam int userId, 
												@RequestParam int catId){
		
		return new ResponseEntity<>(service.createPost(dto, userId, catId), HttpStatus.CREATED);
	}
	
	@GetMapping("/get-post-by-user")
	public ResponseEntity<List<PostDto>> getPostsByUser(@RequestParam int userId){
		
		return new ResponseEntity<> (service.getPostByUser(userId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get-post-by-category")
public ResponseEntity<List<PostDto>> getPostsByCategory(@RequestParam int catId){
		
		return new ResponseEntity<> (service.getPostByCategory(catId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get-all-post")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(defaultValue = "5", required = false)int pageSize,
			@RequestParam(defaultValue = "0", required = false)int pageNumber,
			@RequestParam (defaultValue = "id",required = false)String sortBy,
			@RequestParam (defaultValue = "asc", required = false)String sortDir){
		return new ResponseEntity<> (service.getAllPosts(pageSize, pageNumber, sortBy, sortDir), HttpStatus.FOUND);
	}
	
	@GetMapping("/get-post-by-id")
	public ResponseEntity<PostDto> getPostById(int postId){
		return new ResponseEntity<> (service.getPostById(postId), HttpStatus.FOUND);
	}

	@PutMapping("/update")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @RequestParam int postId){
			
		return new ResponseEntity<> (service.updatePost(dto, postId), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deletePost(@RequestParam int postId){
		return new ResponseEntity<> (service.deletePost(postId), HttpStatus.OK);
	}
}
