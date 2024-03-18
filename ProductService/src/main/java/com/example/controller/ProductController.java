package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ProductNotFoundException;
import com.example.model.ProductRequest;
import com.example.model.ProductResponse;
import com.example.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// Create New Product
	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
		ProductResponse savedProduct = productService.createProduct(productRequest);
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}
	
	// Get Single Product
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
		ProductResponse productResponse = productService.getSingleProduct(productId);
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}
	
	// Update Quantity When Place the Order
	@PutMapping("/updateQuantity/{id}")
	public ResponseEntity<Void> updateQuantity(@PathVariable("id") Long productId, @RequestParam int quantity) throws ProductNotFoundException {
		productService.updateQuantity(productId, quantity);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
