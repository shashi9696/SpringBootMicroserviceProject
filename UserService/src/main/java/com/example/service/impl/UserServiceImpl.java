package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.exception.CustomUserNotFoundException;
import com.example.model.UserRequest;
import com.example.model.UserResponse;
import com.example.repository.UserRepository;
import com.example.service.UserService;

import lombok.extern.log4j.Log4j2;
@Service
@Log4j2
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	// Create New User
	@Override
	public UserResponse createUser(UserRequest userRequest) {
		User savedUser = User.builder()
				.userName(userRequest.getUserName())
				.address(userRequest.getAddress())
				.email(userRequest.getEmail())
				.build();
		savedUser = userRepository.save(savedUser);
		log.info("User created successfully..");
		
		UserResponse userResponse = UserResponse.builder()
				.userName(savedUser.getUserName())
				.address(savedUser.getAddress())
				.email(savedUser.getEmail())
				.build();
		
		return userResponse;
	}

	// Get Single User
	@Override
	public UserResponse getSingleUser(Long userId) throws CustomUserNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new CustomUserNotFoundException("User not found with id : "+ userId));
		UserResponse userResponse = UserResponse.builder()
				.userName(user.getUserName())
				.address(user.getAddress())
				.email(user.getEmail())
				.build();
		
		return userResponse;
	}

}
