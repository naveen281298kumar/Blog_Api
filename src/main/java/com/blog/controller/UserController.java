package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.blog.dto.UserDto;
import com.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/blog-api/users")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto userCreated = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(userCreated, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @RequestParam int id){
		UserDto userUpdated = userService.updateUser(userDto, id);
		return new ResponseEntity<UserDto>(userUpdated, HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> allUsers = userService.getAllUser();
		return new ResponseEntity<List<UserDto>> (allUsers, HttpStatus.FOUND);
	}
	
	@GetMapping("/getuser")
	public ResponseEntity<UserDto> getUser(@RequestParam int id){
		UserDto getUser  = userService.getUserById(id);
		return new ResponseEntity<UserDto> (getUser, HttpStatus.FOUND);
	}
	
	@DeleteMapping("/delete-user")
	public ResponseEntity<String> deleteUser(@RequestParam int id){
		String deleteUserMessage = userService.deleteUser(id);
		return new ResponseEntity<> (deleteUserMessage, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-all-users")
	public ResponseEntity<String> deleteAllUsers(){
		return new ResponseEntity<String> (userService.deleteAllusers(),HttpStatus.OK);
	}
	
	
	
	
	
	

}
