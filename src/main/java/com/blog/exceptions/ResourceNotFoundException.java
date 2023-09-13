package com.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	int userId;
	String fieldName;
	
	 public ResourceNotFoundException(String filedName, int userId) {
	
		super(String.format("%s not found with id: %s ",filedName,userId));
		this.userId = userId;
		this.fieldName = fieldName;
	}

}
