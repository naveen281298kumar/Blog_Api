package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import static com.blog.constants.ApplicationConstants.PAGE_NUMBER;
import static com.blog.constants.ApplicationConstants.PAGE_SIZE;
import static com.blog.constants.ApplicationConstants.SORT_BY;
import static com.blog.constants.ApplicationConstants.SORT_DIR;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.service.FileServiceImpl;
import com.blog.service.PostServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/blog-api/posts")
public class PostController {
	
	@Autowired
	private PostServiceImpl service;
	
	@Autowired
	private FileServiceImpl fileService;
	
	@Value("${project.image}")
	private String path;
	
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
			@RequestParam(defaultValue = PAGE_SIZE , required = false)int pageSize,
			@RequestParam(defaultValue = PAGE_NUMBER, required = false)int pageNumber,
			@RequestParam (defaultValue = SORT_BY,required = false)String sortBy,
			@RequestParam (defaultValue = SORT_DIR, required = false)String sortDir){
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
	
//	method to upload a file
	@PostMapping("/image/upload")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
														 @RequestParam int postId) throws IOException
	{
		PostDto dto = service.getPostById(postId);
		String fileName = fileService.uploadImage(path, image);
		dto.setImageName(fileName);
		PostDto updatePost = service.updatePost(dto, postId);
		return new ResponseEntity<> (updatePost, HttpStatus.OK);
		
	}
	
//  method to serve a file
	@GetMapping(value = "/image/get", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
								@RequestParam("image") String imageName,
								HttpServletResponse response
		)throws IOException{
		
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}
