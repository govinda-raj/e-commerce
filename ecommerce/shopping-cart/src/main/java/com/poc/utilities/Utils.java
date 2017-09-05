package com.poc.utilities;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;

//import com.poc.model.ProductMerchant;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.Constants;
//import com.poc.model.Cart;
//import com.poc.model.Product;

import com.coviam.model.*;


public class  Utils {

	public  final static Logger LOGGER= LoggerFactory.getLogger(Utils.class);

	public static String  getEmail(HttpSession session){
		String email;
		email=(String)session.getAttribute("email");
		return email;
	}
	public static List<Cart> getCartList(HttpSession session){
		List<Cart> cartList;
	if(session.getAttribute("cartList")!=null){
		cartList=(List<Cart>) session.getAttribute("cartList");
	}
	else {
		cartList=new ArrayList<Cart>();
	}
		return cartList;
	}
	public static boolean isPresentInCart(Integer productId,HttpSession session){
		List<Cart> cartList=(List<Cart>) session.getAttribute("cartList");
		boolean status=false;
		if(cartList!=null){
			LOGGER.debug("Cart : {} ",cartList );
		for(Cart cart:cartList){
			if(productId.equals(cart.getProductId())){
				status = true;
				LOGGER.debug("Status : {} ",status);
				break;
			}
		}}
		return status;
	}
	public static Product getProductByProductId(Integer productId){
		RestTemplate rt=new RestTemplate();
		String productJson = rt.getForObject(Constants.PRODUCT_URL
				+ "/getProductById?productId={productId}", String.class,
				productId);
		ObjectMapper mapper=new ObjectMapper();
		Product product=null;
		try {
			product = mapper.readValue(productJson,Product.class);
		} catch (IOException e) {
			
		}
		return product;
	}
	public static boolean isInteger(String s){
		boolean status=false;
        try {
          int n=Integer.parseInt(s);
        } catch (NumberFormatException e) {
        	status= true;
        }
        return status;
   }
	public static boolean isValidEmailAddress(String email) {
        boolean result = false;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = true;
        }
        return result;
    }

	public static Double configFactor(Float merchantRating,Double productPrice, Integer productQuantity ){
		Double factor=(Constants.MERCHANT_RATING_WEIGHTAGE*merchantRating + Constants.STOCK_WEIGHTAGE* productQuantity)/(10* productPrice);
		factor = BigDecimal.valueOf(factor).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return factor;
	}


	public static Boolean isValidProductPrice(String productPrice) {
		Boolean status=false;
		if(productPrice == null || productPrice.equals("")
				|| !productPrice.matches("[0-9]*\\.?[0-9]*")){
			status=true;
		}
		return status;
	}

	public static Boolean isValidProductQuantity(String productQuantity){
		Boolean status=false;
		if (productQuantity == null || productQuantity.equals("") || Utils.isInteger(productQuantity)) {
			status=true;
		}
	return status;
		}

		public static Boolean isValidProductName(String productName) {
			Boolean status = false;
			if (productName == null || productName.equals("") || productName.matches("[0-9]*")) {
				status = true;
			}
			return status;
		}

}
