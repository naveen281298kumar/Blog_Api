package com.blog.exceptions;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.dto.ErrorInfo;

@RestControllerAdvice
public class GlobalException {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorInfo> resourceNotFoundException(ResourceNotFoundException ex){
		
		ErrorInfo error = new ErrorInfo();
		error.setError_code(HttpStatus.NOT_FOUND.value());
		error.setSuccess(false);
		error.setError_message(ex.getMessage());
		error.setError_time(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			String filedName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			response.put(filedName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(WrongCredentialException.class)
	public ResponseEntity<ErrorInfo> wrongCredentialException(WrongCredentialException ex){
		
		ErrorInfo error = new ErrorInfo();
		error.setError_code(HttpStatus.BAD_REQUEST.value());
		error.setSuccess(false);
		error.setError_message(ex.getMessage());
		error.setError_time(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.BAD_REQUEST);
	}
}
