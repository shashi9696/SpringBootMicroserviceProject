package com.example.service;

import com.example.model.PaymentRequest;
import com.example.model.PaymentResponse;

public interface PaymentService {

	// Do Payment
	PaymentResponse doPayment(PaymentRequest paymentRequest);

	// Get Single Placed Order
	PaymentResponse getPaymentDetails(Long orderId);

}
