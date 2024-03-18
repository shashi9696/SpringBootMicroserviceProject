package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.CustomUserNotFoundException;
import com.example.model.UserRequest;
import com.example.model.UserResponse;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Create New User
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
		UserResponse createdUser = userService.createUser(userRequest);
		return new ResponseEntity<UserResponse>(createdUser, HttpStatus.OK);
	}
	
	// Get Single User
	@GetMapping("/{id}")
	public ResponseEntity<?> getSingleUser(@PathVariable("id") Long userId) throws CustomUserNotFoundException {
		UserResponse userResponse = userService.getSingleUser(userId);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

}
