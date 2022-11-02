package com.blog.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.app.entity.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user from database by username
	User user=	this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "email : "+username, 0));
	return user;
	
		
	}

}
