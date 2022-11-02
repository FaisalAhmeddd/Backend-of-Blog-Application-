package com.blog.app.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.UserDto;
import com.blog.app.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto =this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{user_id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("user_id")int user_id){
		UserDto updatedUser = this.userService.updateUser(userDto, user_id);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	//only admin can call this 
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{user_id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("user_id")int user_id){
		this.userService.deleteUser(user_id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return  ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{user_id}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable int user_id){
		return  ResponseEntity.ok(this.userService.getUserById(user_id));
	}


}
