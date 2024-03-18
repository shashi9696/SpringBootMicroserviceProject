package com.example.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PAYMENT_DETAILS")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long paymentId;
	
	private Long orderId;
	private Double amount;
	private String paymentMode;
	private Instant paymentDate;
	private String paymentStatus; 

}
