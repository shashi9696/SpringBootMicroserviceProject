package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
	
	private Long productId;
	private Double amount;
	private int quantity;
	private String orderStatus;
	private String paymentStatus;
}
