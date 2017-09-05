package com.cservice.service;

import java.util.List;

//import com.cservice.model.Cart;
import com.coviam.model.Cart;


public interface CartService {

	/**
	 * Adding into cart
	 * @param cart
	 */
	public void addToCart(Cart cart);

	/**
	 * Remove from cart for given email and productid
	 * @param email
	 * @param productId
	 * @return
	 */
	public boolean removeFromCartByUserEmailProductId(String email,Integer productId);

	/**
	 * Get CArt by user email
	 * @param userMail
	 * @return
	 */
	public List<Cart> getCartByUserEmail(String userMail);

	/**
	 * remove cart by user email
	 * @param userEmail
	 */
	public void removeCartByUserEmail(String userEmail);

	/**
	 * update quantity of product
	 * @param productQuantity
	 * @param id
	 */
	public void updateProductQuantityById(int productQuantity, int id);

	/**
	 * Remove cart by given Id(cart id - auto generated)
	 * @param id
	 */
	public void removeById(Integer id);
}
