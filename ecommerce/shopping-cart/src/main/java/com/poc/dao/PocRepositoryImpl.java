package com.poc.dao;

import java.util.List;

//import com.poc.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.poc.Constants;


import com.coviam.model.*;
@Repository
public class PocRepositoryImpl implements PocRepository{
	private final Logger LOGGER=LoggerFactory.getLogger(this.getClass());
	public RestTemplate getRestTemplate(){
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate;
	}
	
	@Override
	public String getCartDetails(String userEmail){
		String cartJson=null;
		cartJson = getRestTemplate().getForObject(Constants.CART_URL+Constants.GET_CART_BY_USEREMAIL, String.class,userEmail);
		return cartJson;
	}

	@Override
	public String getCategories() {
		String categoriesJson=null;
		try {
			categoriesJson = getRestTemplate().getForObject(Constants.PRODUCT_URL+Constants.GET_CATEGORIES,String.class);
		} catch (RestClientException e) {
			LOGGER.error(e.getMessage());
		}
		return categoriesJson;
	}
	
	@Override
	public Product getProductByProductIdAndMerchantId(Integer productId,Integer merchantId){
		Product product=null;
		product = getRestTemplate().getForObject(Constants.PRODUCT_URL+ Constants.GET_PRODUCT_BY_ID_AND_Merchant_ID,Product.class,productId,merchantId);
		LOGGER.debug("Product : {} ",product);
		return product;
	}

	@Override
	public Cart addToCart(Cart cart) {
		cart = getRestTemplate().postForObject(Constants.CART_URL + Constants.ADD_CART, cart,Cart.class);
		return cart;
	}

	@Override
	public void removeCartById(int cartId) {
		getRestTemplate().delete(Constants.CART_URL+Constants.REMOVE_CART_BY_ID,cartId);
	}

	@Override
	public boolean updateProductQuantityById(int productQunatity,int cartId) {
		getRestTemplate().getForObject(Constants.CART_URL+ Constants.UPDATE_PRODUCT_QUANTITY_BY_ID,Boolean.class, productQunatity, cartId);
		return false;
	}

	@Override
	public void removeCartByUserEmail(String userEmail) {
		getRestTemplate().delete(Constants.CART_URL+Constants.REMOVE_CART_BY_USEREMAIL,userEmail);
	}

	@Override
	public String getAllProducts(int pageNumber) {
		String productsJson=null;
		try {
			productsJson = getRestTemplate().getForObject(Constants.PRODUCT_URL + Constants.GET_ALL_PRODUCTS,String.class,pageNumber);
		} catch (RestClientException e) {
			LOGGER.error(e.getMessage());
		}
		return productsJson;
	}

	@Override
	public ProductTable addProduct(ProductTable product) {
		LOGGER.debug("Before Added product in product table :{}",product);
		product=getRestTemplate().postForObject(Constants.PRODUCT_URL + Constants.ADD_PRODUCT,product, ProductTable.class);
		LOGGER.debug("After Added product in product table :{}",product);
		return product;
	}

	@Override
	public Order placeOrder(Order order) {
		order = getRestTemplate().postForObject(Constants.ORDER_URL + Constants.PLACE_ORDER, order, Order.class);
		LOGGER.debug("Order details : {} ",order);
		return order;
	}

	@Override
	public void reduceQuantity(List<Cart> cList) {
		for (Cart cart : cList) {
			LOGGER.debug("Reducing quantity for cart :{} " ,cart.getId() + " product ID : {} ", cart.getProductId());
			Boolean status = getRestTemplate().getForObject(Constants.PRODUCT_URL+Constants.REDUCE_QUANTITY, Boolean.class,cart.getProductId(), cart.getProductQuantity(),cart.getMerchantId());
		}
	}

	public Integer getMerchantByProductId(Integer id){
		return getRestTemplate().getForObject(Constants.PRODUCT_URL+Constants.GET_MERCHANT,Integer.class,id);
	}

	public String getMerchantEmail(Integer merchantId){
		return getRestTemplate().getForObject(Constants.MERCHANT_URL+Constants.GET_MERCHANT_EMAIL,String.class,merchantId);
	}

	public String getAllSameProduct(String productId,Integer page){
		String productsJson=null;
		try {
			productsJson = getRestTemplate().getForObject(Constants.PRODUCT_URL + Constants.GET_PRODUCT,String.class,productId,page);
		} catch (RestClientException e) {
			LOGGER.error(e.getMessage());
		}
		return productsJson;
	}

	public Merchant getMerchant(Integer merchantId){
		Merchant merchant=getRestTemplate().getForObject(Constants.MERCHANT_URL+Constants.GET_MERCHANT_INFO,Merchant.class,merchantId);
		LOGGER.debug("Merchant from Merchant Microservice : {}",merchant);
		return merchant;
	}

	public Merchant getMerchantByEmail(String merchantEmail){
		Merchant merchant=getRestTemplate().getForObject(Constants.MERCHANT_URL+Constants.GET_MERCHANT_BY_EMAIL,Merchant.class,merchantEmail);
		LOGGER.debug("Merchant from Merchant Microservice : {}",merchant);
		return merchant;
	}

}