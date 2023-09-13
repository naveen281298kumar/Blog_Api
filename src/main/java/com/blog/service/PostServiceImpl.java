package com.blog.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.repository.CategoryRepo;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;
import com.blog.exceptions.ResourceNotFoundException;

@Service
public class PostServiceImpl {
	
	@Autowired
	private PostRepo repo;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private ModelMapper mapper;

	public PostDto createPost(PostDto dto, int userId,int catId) {
		
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", userId));
		
		
		Category category = catRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category", catId)); 
		
		Post post = mapper.map(dto, Post.class);
		post.setCategory(category);
		post.setUser(user);
		post.setDate(new Date());
		post.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
		
		Post postCreated = repo.save(post);
		return mapper.map(postCreated, PostDto.class);
	}
	
	public List<PostDto> getPostByUser(int userId){
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", userId));
		List<Post> posts = repo.findByUser(user);
		return posts.stream().map(post-> mapper.map(post, PostDto.class)).collect(Collectors.toList());
	}
	
	public List<PostDto> getPostByCategory(int catId){
		
		Category cat = catRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category", catId));
		List<Post> posts = repo.findByCategory(cat);
		return posts.stream().map(post-> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
	}
	
	public PostResponse getAllPosts(int pageSize, int pageNumber, String sortBy, String sortDir){
		
		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> postsPerPage = repo.findAll(page);
		List<Post> posts = postsPerPage.getContent();

		List<PostDto> dto = posts.stream().map(post-> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse response = new PostResponse();
		
		response.setPostContent(dto);
		response.setPageNumber(postsPerPage.getNumber());
		response.setPageSize(postsPerPage.getSize());
		response.setTotalElements(postsPerPage.getTotalElements());
		response.setTotalPages(postsPerPage.getTotalPages());
		response.setLastPage(postsPerPage.isLast());
		
		return response;
	}
	
	public PostDto getPostById(int postId) {
		Post post = repo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", postId));
		return mapper.map(post, PostDto.class);
	}
	
	public PostDto updatePost(PostDto dto, int id) {
		
		Post post = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post",id));
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));

		Post postUpdated = repo.save(post);
		
		return mapper.map(postUpdated, PostDto.class);
	}
	
	public String deletePost(int postId) {
		
		repo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post",postId));
		repo.deleteById(postId);
		return "Post deleted successfully";
		
	}
	
}
