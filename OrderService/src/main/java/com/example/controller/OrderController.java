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

import com.example.exception.OrderNotFoundException;
import com.example.model.OrderRequest;
import com.example.model.OrderResponse;
import com.example.model.PlacedOrderResponse;
import com.example.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	// Place New Order
	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
		OrderResponse placedOrder = orderService.placeOrder(orderRequest);
		return new ResponseEntity<>(placedOrder, HttpStatus.OK);
	}
	
	// Get Current Order Details
	@GetMapping("/getPlacedOrder/{id}")
	public ResponseEntity<?> getPlacedOrderDetails(@PathVariable("id") Long orderId) throws OrderNotFoundException {
		PlacedOrderResponse placedOrderResponse = orderService.getPlacedOrderDetails(orderId);
		return new ResponseEntity<PlacedOrderResponse>(placedOrderResponse, HttpStatus.OK);
	}
	
	// Get Single Order
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderDetails(@PathVariable("id") Long orderId) throws OrderNotFoundException {
		OrderResponse orderResponse = orderService.getOrderDetails(orderId);
		return new ResponseEntity<>(orderResponse, HttpStatus.OK);
	}

}
