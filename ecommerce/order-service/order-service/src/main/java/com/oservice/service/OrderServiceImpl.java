package com.oservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oservice.dao.OrderRepository;
//import com.oservice.model.Order;
import org.springframework.transaction.annotation.Transactional;
import com.coviam.model.Order;


@Service
public class OrderServiceImpl implements OrderService{


	@Autowired
	private OrderRepository repository;
	
	@Override
	public List<Order> getOrderDetailsByUserEmail(String userEmail) {
		return repository.findByUserEmail(userEmail);
	}

	@Transactional(readOnly = false)
	public void placeOrder(Order order){
	    repository.save(order);
    }
}
