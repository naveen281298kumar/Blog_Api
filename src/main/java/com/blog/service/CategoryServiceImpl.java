package com.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CategoryDto;
import com.blog.entity.Category;
import com.blog.repository.CategoryRepo;
import com.blog.exceptions.ResourceNotFoundException;

@Service
public class CategoryServiceImpl {

	@Autowired
	private CategoryRepo repo;
	@Autowired
	private ModelMapper mapper;
	
	public CategoryDto createCategory(CategoryDto dto) {
		Category category = mapper.map(dto, Category.class);
		return mapper.map(repo.save(category),CategoryDto.class);
	}
	
	public CategoryDto updateCategory(CategoryDto dto, int categoryId) {
		
		Category category = repo.findById(categoryId)
								.orElseThrow(()->new ResourceNotFoundException("Category",categoryId));
		category.setCategoryDescription(dto.getCategoryDescription());
		category.setCategoryTitle(dto.getCategoryTitle());
		return mapper.map(repo.save(category), CategoryDto.class);
	}
	
	public CategoryDto getCategory(int categoryId) {
		
		Category category = repo.findById(categoryId)
								.orElseThrow(()->new ResourceNotFoundException("Category",categoryId));
		
		return mapper.map(category, CategoryDto.class);
	}
	
	public List<CategoryDto> gerAllCategories(){
		
		return repo.findAll()
					.stream()
					.map(category -> mapper.map(category, CategoryDto.class))
					.collect(Collectors.toList());
	} 
	
	public String deleteCategory(Integer categoryId) {
		
		repo.findById(categoryId)
			.orElseThrow(()-> new ResourceNotFoundException("Category",categoryId));
		repo.deleteById(categoryId);
		return "Category deleted successfully";
	}

	public String deleteAllCategories() {
	
		repo.deleteAll();
		return "All categories deleted successfully";
  }
}






