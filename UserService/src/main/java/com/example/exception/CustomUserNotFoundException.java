package com.example.exception;

public class CustomUserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4330844426028504938L;

	public CustomUserNotFoundException() {
		super("resource not found on server !!");
	}
	
	public CustomUserNotFoundException(String message) {
		super(message);
	}

}
