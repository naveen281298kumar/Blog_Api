package com.blog.service;

import java.util.List;

import com.blog.dto.UserDto;
import com.blog.entity.User;

public interface UserService {
	
	UserDto createUser (UserDto userDto);
	UserDto updateUser (UserDto userDto, Integer id);
	UserDto getUserById (Integer id);
	List<UserDto> getAllUser ();
	String deleteUser (Integer id);
	public String deleteAllusers();	

}
