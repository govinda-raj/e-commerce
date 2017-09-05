package com.oservice.controller;

import java.util.List;

import com.oservice.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.oservice.model.Order;
import com.coviam.model.Order;
import com.oservice.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	private final static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@RequestMapping(value= Constants.GET_ORDER_DETAILS_BY_USEREMAIL, method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getOrderDetailsByUserEmail(@RequestParam("userEmail") String userEmail){
		LOGGER.debug("Getting order details for user email : " +userEmail);
		List<Order> orderList=orderService.getOrderDetailsByUserEmail(userEmail);
		HttpStatus status = HttpStatus.OK;
		if(orderList.isEmpty()){
			status = HttpStatus.NOT_FOUND;
			LOGGER.info("Order List for user email : " +userEmail +"is empty");
		}
		return new ResponseEntity<List<Order>>(orderList,status);
	}

	@RequestMapping(value = Constants.PLACE_ORDER, method = RequestMethod.POST)
    public Order placingTheOrder(@RequestBody Order order){
		LOGGER.debug("Placing the order for user : " +order.getUserEmail());
        orderService.placeOrder(order);
	    return order;
    }
}
