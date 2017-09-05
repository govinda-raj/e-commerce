package com.cservice.controller;

import java.util.List;

import com.cservice.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.cservice.model.Cart;
import com.cservice.service.CartService;
import com.coviam.model.Cart;

@RestController
@RequestMapping(value="/cartApi")
public class CartController {

	@Autowired
	private CartService cartService;

	private final static Logger LOGGER = LoggerFactory.getLogger(CartController.class);
	
	@RequestMapping(value= Constants.ADD_CART,method=RequestMethod.POST)
	public ResponseEntity<Cart> addToCart(@RequestBody Cart cart){
		LOGGER.info("New cart for userEmail : " +cart.getUserEmail() + "is added.");
		cartService.addToCart(cart);
		return new ResponseEntity<Cart>(cart,HttpStatus.CREATED);
	}
	
	@RequestMapping(value=Constants.REMOVE_FROM_CART_BY_USEREMAIL_PRODUCTID,method=RequestMethod.DELETE)
	public boolean removeFromCartByUserEmailProductId(@RequestParam("userEmail")String email,@RequestParam("productId")Integer productId){
		boolean deleteStatus=cartService.removeFromCartByUserEmailProductId(email, productId);
		LOGGER.debug("Removing cart from table for userEmail : " +email + ".");
		return deleteStatus;
	}
	
	@RequestMapping(value= Constants.GET_CART_BY_USEREMAIL)
	public ResponseEntity<List<Cart>> getCartByUserEmail(@RequestParam("userEmail") String userEmail){
		LOGGER.debug("Getting cart from table for userEmail : " +userEmail + ".");
		return new ResponseEntity<List<Cart>>(cartService.getCartByUserEmail(userEmail),HttpStatus.OK);
		
	}
	
	@RequestMapping(value=Constants.REMOVE_CART_BY_USEREMAIL,method=RequestMethod.DELETE)
	public void removeCartByUserEmail(@RequestParam("userEmail") String userEmail){
		cartService.removeCartByUserEmail(userEmail);
		LOGGER.debug("Removing cart from table for userEmail : " +userEmail + ".");
	}
	
	@RequestMapping(value = Constants.UPDATE_PRODUCT_QUANTITY_BY_ID)
	public boolean updateProductQuantityById(@RequestParam("productQuantity") int  productQuantity,@RequestParam("cartId") int cartId){
		cartService.updateProductQuantityById(productQuantity,cartId);
		LOGGER.debug("Updating  quantity in cart from table for cart Id : " +cartId + ".");
		return true;
	}
	@RequestMapping(value = Constants.REMOVE_CART_BY_ID,method=RequestMethod.DELETE)
    public void removeById(@RequestParam("id") Integer id){
		cartService.removeById(id);
		LOGGER.debug("Removing cart from table for cart id : " +id + ".");

	}
}
