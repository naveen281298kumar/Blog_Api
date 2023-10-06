package com.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.blog.constants.ApplicationConstants;
import com.blog.entity.Role;
import com.blog.entity.Role.Role_Name;
import com.blog.repository.RoleRepo;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println(encoder.encode("naveen54321"));
		
		try {
			
			Role role1 = new Role();
			role1.setId(ApplicationConstants.ADMIN_USER);
			role1.setRole(Role_Name.ADMIN);
			
			Role role2 = new Role();
			role2.setId(ApplicationConstants.NORMAL_USER);
			role2.setRole(Role_Name.NORMAL);
			
			Set<Role> roles = Set.of(role1, role2);
			
			List<Role> result = roleRepo.saveAll(roles);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
