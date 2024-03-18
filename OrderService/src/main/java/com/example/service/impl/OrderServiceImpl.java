package com.example.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Order;
import com.example.exception.OrderNotFoundException;
import com.example.model.OrderRequest;
import com.example.model.OrderResponse;
import com.example.model.PaymentResponse;
import com.example.model.PlacedOrderResponse;
import com.example.model.ProductResponse;
import com.example.model.client.PaymentRequest;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
import com.example.service.client.PaymentService;
import com.example.service.client.ProductService;
import com.example.utility.OrderStatus;

import lombok.extern.log4j.Log4j2;
@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;

	// Place New Order
	@Override
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		// Check product's quantity is available or not.
		productService.updateQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		Order order = Order.builder()
				.productId(orderRequest.getProductId())
				.amount(orderRequest.getAmount() * orderRequest.getQuantity())
				.quantity(orderRequest.getQuantity())
				.orderStatus(OrderStatus.CREATED)
				.paymentStatus(OrderStatus.PENDING)
				.orderDate(Instant.now())
				.build();
		
		order = orderRepository.save(order);
		
		log.info("Calling payment service with order Id : "+ order.getOrderId());
		PaymentRequest paymentRequest = PaymentRequest.builder()
				.orderId(order.getOrderId())
				.amount(order.getAmount())
				.paymentMode(orderRequest.getPaymentMode())
				.build();
		
		String status = "";
		try {
			paymentService.doPayment(paymentRequest);
			status = OrderStatus.SUCCESS;
		} catch(Exception e) {
			log.info("paymentRequest "+ paymentRequest);
			status = OrderStatus.FAILED;
		}
		order.setOrderStatus(status);
		order.setPaymentStatus(status);
		order = orderRepository.save(order);
		
		OrderResponse placedOrder = OrderResponse.builder()
				.productId(order.getProductId())
				.amount(order.getAmount())
				.quantity(order.getQuantity())
				.orderStatus(order.getOrderStatus())
				.paymentStatus(order.getPaymentStatus())
				.build();
		return placedOrder;
	}
	
	// Get Placed Order
	@Override
	public PlacedOrderResponse getPlacedOrderDetails(Long orderId) throws OrderNotFoundException {
		log.info("Calling order details...");
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found with order id :"+ orderId));
		log.info("Calling product service to product details with id :: "+ orderId);
		ProductResponse productResponse = productService.getSingleProduct(order.getProductId()).getBody();
		log.info("product info details : "+productResponse);
		
		log.info("Calling payment details");
		PaymentResponse paymentResponse = paymentService.getPaymentDetails(order.getOrderId()).getBody();
		PlacedOrderResponse orderResponse = PlacedOrderResponse.builder()
				.orderId(order.getOrderId())
				.productId(order.getProductId())
				.quantity(order.getQuantity())
				.amount(order.getAmount())
				.productResponse(productResponse)
				.paymentResponse(paymentResponse)
				.build();
		return orderResponse;
	}

	// Get Single Order
	@Override
	public OrderResponse getOrderDetails(Long orderId) throws OrderNotFoundException {

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found with id :: "+ orderId));

		OrderResponse orderResponse = OrderResponse.builder()
				.productId(order.getProductId())
				.amount(order.getAmount())
				.quantity(order.getQuantity())
				.orderStatus(order.getOrderStatus())
				.paymentStatus(order.getPaymentStatus())
				.build();
		return orderResponse;
	}
	
	

}
