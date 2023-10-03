package com.blog.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.constants.ApplicationConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

	@Pattern(regexp = ApplicationConstants.IMAGE_FORMAT_REGEX, message = ApplicationConstants.INVALID_IMAGE_FORMAT_MESSAGE)
	private String imageName;

	private CategoryDto category;

	private UserDto user;

	private Set<CommentDto> comments = new HashSet();
}
