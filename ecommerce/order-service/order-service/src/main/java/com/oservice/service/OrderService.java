package com.oservice.service;

import java.util.List;

//import com.oservice.model.Order;

import com.coviam.model.Order;


public interface OrderService {

    /**
     * Get all order details for ptovided email id
     * @param userEmail
     * @return
     */
	public List<Order> getOrderDetailsByUserEmail(String userEmail);

    /**
     * Placing order
     * @param order
     */
    public void placeOrder(Order order);

}
