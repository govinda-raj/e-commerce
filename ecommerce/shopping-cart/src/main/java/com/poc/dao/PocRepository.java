package com.poc.dao;

import java.util.List;

//import com.poc.model.*;
import com.coviam.model.*;

public interface PocRepository {
	public String getCartDetails(String userEmail) throws Exception;
	public String getCategories();
	public Product getProductByProductIdAndMerchantId(Integer productId,Integer merchantId);
	public Cart addToCart(Cart cart);
	public void removeCartById(int cartId);
	public boolean updateProductQuantityById(int productQunatity,int cartId);
	public void removeCartByUserEmail(String userEmail);
	public String getAllProducts(int pageNumber);
	public ProductTable addProduct(ProductTable product);
	public Order placeOrder(Order order);
	public void reduceQuantity(List<Cart> cList);
	public Integer getMerchantByProductId(Integer id);
	public String getMerchantEmail(Integer merchantId);
	public String getAllSameProduct(String productId,Integer page);
	public Merchant getMerchant(Integer merchantId);

	public Merchant getMerchantByEmail(String merchantEmail);

	}
