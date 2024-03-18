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

import com.example.model.PaymentRequest;
import com.example.model.PaymentResponse;
import com.example.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	// DO Payment
	@PostMapping("/doPayment")
	public ResponseEntity<?> doPayment(@RequestBody PaymentRequest paymentRequest) {
		PaymentResponse paymentResponse = paymentService.doPayment(paymentRequest);
		return new ResponseEntity<PaymentResponse>(paymentResponse, HttpStatus.OK);
	}
	
	// Get Single Placed Order
	@GetMapping("/{id}")
	public ResponseEntity<?> getPaymentDetails(@PathVariable("id") Long orderId) {
		PaymentResponse paymentResponse = paymentService.getPaymentDetails(orderId);
		return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
	}

}
