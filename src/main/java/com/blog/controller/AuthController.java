package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.JwtAuthRequest;
import com.blog.dto.JwtAuthResponse;
import com.blog.dto.UserDto;
import com.blog.exceptions.WrongCredentialException;
import com.blog.security.JwtTokenHelper;
import com.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
        @RequestBody JwtAuthRequest request
    ){
            authenticate(request.getUserName(), request.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
            String token = jwtTokenHelper.generateToken(userDetails);
            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(token);
            return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);

    }

    private void authenticate(String userName, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password); 
        try {
        	authenticationManager.authenticate(authenticationToken);
        }
        catch(BadCredentialsException ex) {
        	throw new WrongCredentialException("Invalid Username or Password");
        }
    }
    
    
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
    	
    	UserDto registeruser = userService.registerUser(userDto);
    	
    	return new ResponseEntity<>(registeruser, HttpStatus.CREATED);
    }
    
    
    
}
