package com.blog.dto;

import static com.blog.constants.ApplicationConstants.EMAIL_REGEX;
import static com.blog.constants.ApplicationConstants.PASSWORD_REGEX;
import static com.blog.constants.ApplicationConstants.INVALID_EMAIL_FORMAT_MESSAGE;
import static com.blog.constants.ApplicationConstants.INVALID_PASSWORD_FORMAT_MESSAGE;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min=3, message = "minimum should be 3 charachters")
	private String name;
	
	@NotEmpty
	@Size(min=7,message="please enter the valid email , your email length should be atleast 7")
	@Email(regexp = EMAIL_REGEX,message = INVALID_EMAIL_FORMAT_MESSAGE)
	private String email;
	
	@NotEmpty()
	@Size(min = 8, message = "Password value should be 8 atleast")
	@Pattern(regexp = PASSWORD_REGEX, message = INVALID_PASSWORD_FORMAT_MESSAGE)
	private String password;
	
	@NotEmpty(message="Field can not be empty")
	private String about;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}

	
}
