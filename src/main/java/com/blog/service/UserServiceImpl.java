package com.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.UserDto;
import com.blog.entity.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = mapper.map(userDto, User.class);
		User userCreated = userRepo.save(user);
		return mapper.map(userCreated, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		
		User user = userRepo.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("User",id));
		
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		User userUpdated = userRepo.save(user);
		return mapper.map(userUpdated, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer id) {
		
		User user = userRepo.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("User",id));
		
		return mapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		
		List<User> allUsers = userRepo.findAll();
		List<UserDto> userDtos = allUsers.stream()
										 .map(user-> mapper.map(user, UserDto.class))
										 .collect(Collectors.toList());
	
		return userDtos;
	}

	@Override
	public String deleteUser(Integer id) {
		
		User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User",id));
		userRepo.deleteById(id);
		return "user deleted successfully";
	}
	
	public String deleteAllusers() {
		
		userRepo.deleteAll();
		return "All users deleted successfully";
	}

}
