package com.example.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.PaymentResponse;
import com.example.model.client.PaymentRequest;

@FeignClient(name="PAYMENT-SERVICE/api/payment")
public interface PaymentService {
	
	@PostMapping("/doPayment")
	public ResponseEntity<?> doPayment(@RequestBody PaymentRequest paymentRequest);
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable("id") Long orderId);

}
