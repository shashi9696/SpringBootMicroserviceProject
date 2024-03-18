package com.example.model;

import com.example.enums.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
	
	private Long orderId;
	private PaymentMode paymentMode;
	private Double amount;

}
