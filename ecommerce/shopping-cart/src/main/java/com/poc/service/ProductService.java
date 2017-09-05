package com.poc.service;

import java.util.List;

//import com.poc.model.*;
import org.springframework.data.domain.Page;
//import com.poc.utilities.Product;
import com.coviam.model.*;


public interface ProductService {
	public List<Product> getAllProducts(String url, String key);
	public List<Cart> getCartDetails(String email) throws Exception;
	public List<String> getCategories();
	public Product getProductByProductIdAndMerchantID(Integer productId,Integer merchantId);
	public Cart addToCart(Cart cart);
	public void removeCartById(int cartId);
	public boolean updateProductQuantityById(int productQunatity,int cartId);
	public void removeCartByUserEmail(String userEmail);
	public Page<Product> getProductsByPage(int pageNumber);
	public ProductTable addProduct(ProductTable product);
	public Order placeOrder(List<Cart> cartList,String userEmail);
	public void reduceQuantity(List<Cart> cartList);
	public Merchant getMerchantInfo(Integer merchantId) ;
	public Page<Product> getAllSameProduct(String productId, int pageNumber) ;
	public void mailToMerchants(Order order);

	public Merchant getMerchantByEmail(String merchantEmail);
	}
