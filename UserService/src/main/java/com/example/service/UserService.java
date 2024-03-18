package com.example.service;

import com.example.exception.CustomUserNotFoundException;
import com.example.model.UserRequest;
import com.example.model.UserResponse;

public interface UserService {

	// Create New User
	public UserResponse createUser(UserRequest userRequest);

	// Get Single User
	public UserResponse getSingleUser(Long userId) throws CustomUserNotFoundException;

}
