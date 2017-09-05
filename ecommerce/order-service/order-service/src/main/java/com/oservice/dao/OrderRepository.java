package com.oservice.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//import com.oservice.model.Order;
import com.coviam.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{
	
	List<Order> findByUserEmail(String userEmail);
	
}
