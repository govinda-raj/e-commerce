package com.cservice.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cservice.dao.CartRepository;
//import com.cservice.model.Cart;
import com.coviam.model.Cart;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository repository;

	@Transactional(readOnly = false)
	public void addToCart(Cart cart) {
		repository.save(cart);	
	}

	@Transactional(readOnly = false)
	public boolean removeFromCartByUserEmailProductId(String email, Integer productId) {
		repository.deleteByUserEmailAndProductId(email,productId);
		return true;
	}

	@Transactional(readOnly = true)
	public List<Cart> getCartByUserEmail(String userMail) {
		return repository.findByUserEmail(userMail);
	}

	@Transactional(readOnly = false)
	public void removeCartByUserEmail(String userEmail) {
		repository.deleteByUserEmail(userEmail);
	}

	@Transactional(readOnly = false)
	public void updateProductQuantityById(int productQuantity, int cartId) {
		repository.updateProductQuantityById(productQuantity,cartId);
	}

	@Transactional(readOnly = false)
	public void removeById(Integer cartId){
	        repository.removeById(cartId);
	    }
}
