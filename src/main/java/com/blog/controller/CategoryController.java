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

import com.blog.dto.CategoryDto;
import com.blog.service.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("blog-api/category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl service;
	
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto dto){
		
		return new ResponseEntity<CategoryDto>(service.createCategory(dto),HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto dto, int id){
		
		return new ResponseEntity<CategoryDto>(service.updateCategory(dto, id), HttpStatus.OK);
	}
	
	@GetMapping("/get-category")
	public ResponseEntity<CategoryDto> getCategory(@RequestParam int id){
		
		return new ResponseEntity<CategoryDto>(service.getCategory(id), HttpStatus.FOUND);
	}
	
	@GetMapping("/get-all-categories")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		
		return new ResponseEntity<List<CategoryDto>>(service.gerAllCategories(),HttpStatus.FOUND);
	}
	
	@DeleteMapping("/delete-category")
	public String deleteCategory(@RequestParam int id) {
		
		return service.deleteCategory(id);
	}
	
	@DeleteMapping("/delete-all-categories")
	public String deleteAllCategories() {
		return service.deleteAllCategories();
	}
}
