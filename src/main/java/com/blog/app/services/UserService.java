package com.blog.app.services;


import java.util.List;

import com.blog.app.payloads.UserDto;

public interface UserService {
	
	UserDto registerNewUser(UserDto userDto);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer user_id);
	UserDto getUserById(Integer user_id);
	List<UserDto> getAllUsers();
	void deleteUser(Integer user_id);
	
	

}
