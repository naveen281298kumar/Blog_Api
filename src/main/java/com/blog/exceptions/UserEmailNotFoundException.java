package com.blog.exceptions;

public class UserEmailNotFoundException extends RuntimeException{

	String email;
	
	 public UserEmailNotFoundException(String email) {
	
		super("User not found with email "+email);
		this.email=email;
	}
    
}
