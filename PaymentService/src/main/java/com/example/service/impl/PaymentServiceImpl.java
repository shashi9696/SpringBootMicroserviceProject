package com.example.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Payment;
import com.example.model.PaymentRequest;
import com.example.model.PaymentResponse;
import com.example.repository.PaymentRepository;
import com.example.service.PaymentService;
import com.example.utility.PaymentStatus;
@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	// Do Payment
	@Override
	public PaymentResponse doPayment(PaymentRequest paymentRequest) {
		Payment payment = Payment.builder()
				.orderId(paymentRequest.getOrderId())
				.amount(paymentRequest.getAmount())
				.paymentMode(paymentRequest.getPaymentMode().name())
				.paymentDate(Instant.now())
				.paymentStatus(PaymentStatus.SUCCESS)
				.build();
		
		payment = paymentRepository.save(payment);
		
		PaymentResponse paymentResponse = PaymentResponse.builder()
				.paymentId(payment.getPaymentId())
				.orderId(payment.getOrderId())
				.amount(payment.getAmount())
				.orderStatus(payment.getPaymentStatus())
				.paymentMode(payment.getPaymentMode())
				.paymentDate(payment.getPaymentDate())
				.build();
		return paymentResponse;
	}

	// Get Single Placed Order
	@Override
	public PaymentResponse getPaymentDetails(Long orderId) {
		Payment payment = paymentRepository.findByOrderId(orderId);
		PaymentResponse paymentResponse = PaymentResponse.builder()
				.orderId(payment.getOrderId())
				.paymentId(payment.getPaymentId())
				.amount(payment.getAmount())
				.orderStatus(payment.getPaymentStatus())
				.paymentMode(payment.getPaymentMode())
				.paymentDate(payment.getPaymentDate())
				.build();
		
		return paymentResponse;
	}
	
	

}
