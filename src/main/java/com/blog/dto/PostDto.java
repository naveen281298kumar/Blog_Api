package com.blog.dto;


import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int id;
	

	@NotBlank
	@Size(max = 100, message = "characters should not be more than 100")
	private String title;
	
	@NotBlank
	@Size(max = 1000, message = "characters should not be more than 1000")
	private String content;
	
	private Date date;
	
	private String imageName;
	
	private CategoryDto category;
	
	private UserDto user;
}
