package com.blog.exceptions;

public class WrongCredentialException extends RuntimeException {

	public WrongCredentialException() {
		super();
		
	}

	public WrongCredentialException(String message) {
		super(message);
		
	}

}
