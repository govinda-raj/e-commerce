package com.poc.controller;

import com.poc.utilities.Utils;
import com.poc.Constants;
//import com.poc.model.Cart;
//import com.poc.model.Item;
//import com.poc.model.Order;
import com.poc.service.ProductService;
import com.poc.utilities.Mail;

import javafx.application.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.SocketPermission;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.coviam.model.*;

import javax.servlet.http.HttpSession;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Created by coviam on 29/07/17.
 */
@RequestMapping(value="/")
@Controller
public class OrderController {

	public  static final  Logger LOGGER= LoggerFactory.getLogger(OrderController.class);


	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
	public String confirmOrder(HttpSession session) {
		LOGGER.debug("Updating cart");
		List<Cart> cartList = Utils.getCartList(session);
		LOGGER.debug("CartList : {} ", cartList);
		//productService.reduceQuantity(cartList);
		LOGGER.debug("Reducing Quantity");
		Order order=productService.placeOrder(cartList, Utils.getEmail(session));
		LOGGER.debug("Placing Order.");
		productService.removeCartByUserEmail(Utils.getEmail(session));
		cartList.clear();
		LOGGER.debug("Mailing to merchants.");
		productService.mailToMerchants(order);
		LOGGER.debug("Sending mail to customer");
		Mail.sendMail(order);
		return "successPage";
	}
	
	@RequestMapping(value = "placeOrder")
	public String placeOrder(HttpSession session, Model model) {
		model.addAttribute("cartList", Utils.getCartList(session));
		model.addAttribute("email", Utils.getEmail(session));
		return "orderDetails";
	}

}
