package com.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private int id;
	
	@NotEmpty(message="Title can not be empty")
	private String categoryTitle;
	
	@NotEmpty(message="Description can not be empty")
	private String categoryDescription;

}
