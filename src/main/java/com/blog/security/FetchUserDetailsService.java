package com.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.blog.entity.User;
import com.blog.exceptions.UserEmailNotFoundException;
import com.blog.repository.UserRepo;

@Component
public class FetchUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // loading user from database by username for authentication
        User user = userRepo.findByEmail(username).orElseThrow(() -> new UserEmailNotFoundException(username));
        return user;
    }

}
