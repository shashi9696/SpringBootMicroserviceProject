package com.example.service;

import com.example.exception.ProductNotFoundException;
import com.example.model.ProductRequest;
import com.example.model.ProductResponse;

public interface ProductService {

	// Create New Product
	public ProductResponse createProduct(ProductRequest productRequest);

	// Get Single Product
	public ProductResponse getSingleProduct(Long productId) throws ProductNotFoundException;

	// Update Quantity When Place the Order
	public void updateQuantity(Long productId, int quantity) throws ProductNotFoundException;
	
}
