package com.blog.service;

import java.util.List;

import com.blog.dto.UserDto;
public interface UserService {
	
	UserDto registerUser(UserDto userDto);
	UserDto createUser (UserDto userDto);
	UserDto updateUser (UserDto userDto, Integer id);
	UserDto getUserById (Integer id);
	List<UserDto> getAllUser ();
	String deleteUser (Integer id);
	String deleteAllusers();	

}
