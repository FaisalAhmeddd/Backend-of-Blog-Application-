package com.blog.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.app.config.AppConstants;
import com.blog.app.entity.Role;
import com.blog.app.entity.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.UserDto;
import com.blog.app.repositories.RoleRepository;
import com.blog.app.repositories.UserRepository;
import com.blog.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer user_id) {
		User user = this.userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepository.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer user_id) {
		User user = this.userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer user_id) {
		this.userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
		this.userRepository.deleteById(user_id);
	}

	public User dtoToUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		//the above method converts UserDto to User
		
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(userDto.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
	User user =	this.modelMapper.map(userDto, User.class);
	//we have encoded the password
	user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		//roles for new users
	Role role= this.roleRepository.findById(AppConstants.NORMAL_USER).get();
	user.getRoles().add(role);
	
	User newUser=this.userRepository.save(user);
	return this.modelMapper.map(newUser, UserDto.class);
	}

}
