package com.example.service;

import com.example.exception.OrderNotFoundException;
import com.example.model.OrderRequest;
import com.example.model.OrderResponse;
import com.example.model.PlacedOrderResponse;

public interface OrderService {

	// Place New Order
	public OrderResponse placeOrder(OrderRequest orderRequest);

	// Get Single Order
	public OrderResponse getOrderDetails(Long orderId) throws OrderNotFoundException;

	// Get Placed Order
	public PlacedOrderResponse getPlacedOrderDetails(Long orderId) throws OrderNotFoundException;

}
