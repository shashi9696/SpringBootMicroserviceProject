package com.example.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.ProductResponse;

@FeignClient(name="PRODUCT-SERVICE/api/product")
public interface ProductService {
	
	@PutMapping("/updateQuantity/{id}")
	public ResponseEntity<Void> updateQuantity(@PathVariable("id") Long productId, @RequestParam int quantity);
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getSingleProduct(@PathVariable("id") Long productId);

}
