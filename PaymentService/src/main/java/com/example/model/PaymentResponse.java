package com.example.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
	
	private Long paymentId;
	private Long orderId;
	private Double amount;
	private String orderStatus;
	private String paymentMode;
	private Instant paymentDate;

}
