package com.example.exception;

public class ProductNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795394257120577811L;

	public ProductNotFoundException() {
		super("resource not found on server !!");
	}
	
	public ProductNotFoundException(String message) {
		super(message);
	}

}
