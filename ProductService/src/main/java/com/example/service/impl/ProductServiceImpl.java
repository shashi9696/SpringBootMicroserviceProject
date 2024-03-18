package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Product;
import com.example.exception.ProductNotFoundException;
import com.example.model.ProductRequest;
import com.example.model.ProductResponse;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	// Create New Product
	@Override
	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.productName(productRequest.getProductName())
				.amount(productRequest.getAmount())
				.quantity(productRequest.getQuantity())
				.build();
		product = productRepository.save(product);
		
		ProductResponse savedProduct = ProductResponse.builder()
				.productName(product.getProductName())
				.amount(product.getAmount())
				.quantity(product.getQuantity())
				.build();
		return savedProduct;
	}

	// Get Single Product
	@Override
	public ProductResponse getSingleProduct(Long productId) throws ProductNotFoundException {
		Product product = null;
		boolean isProduct = productRepository.existsById(productId);
		if(isProduct) {
			product = productRepository.findById(productId).get();
		} else {
			throw new ProductNotFoundException("Product not found with id :: "+productId);
		}
		
		ProductResponse productResponse = ProductResponse.builder()
				.productName(product.getProductName())
				.amount(product.getAmount())
				.quantity(product.getQuantity())
				.build();
		
		return productResponse;
	}

	// Update Quantity When Place the Order
	@Override
	public void updateQuantity(Long productId, int quantity) throws ProductNotFoundException {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with id : "+productId));
		if(product instanceof Product) {
			if(product.getQuantity() < quantity) {
				throw new ProductNotFoundException("Not having enough quantity of products : "+productId);
			}
			product.setQuantity(product.getQuantity() - quantity);
			productRepository.save(product);
		}
	}
	
	

}
