package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;


public interface PostRepo extends JpaRepository<Post, Integer>{
	
	public List<Post> findByCategory(Category cattegory);
	public List<Post> findByUser(User user);
}
