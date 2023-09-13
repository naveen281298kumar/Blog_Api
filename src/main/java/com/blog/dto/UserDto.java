package com.blog.dto;

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
	@Email(regexp = "^[A-Za-z]+[.!A-Za-z0-9]+@[A-Za-z]+[.]{1}[A-Za-z]+$",message = "please enter a valid email")
	private String email;
	
	@NotEmpty()
	@Size(min = 8, message = "Password value should be 8 atleast")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[\\d])(?=.*[!@#$%^&*()])[\\w!@#$%^&*()]{8,24}$", message = "please enter a valid password, there should be atleast one special character, atleast one upper and one lower case character and one digit")
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
