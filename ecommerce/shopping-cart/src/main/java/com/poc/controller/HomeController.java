package com.poc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

//import com.poc.model.Merchant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poc.utilities.Utils;
import com.poc.Constants;
//import com.poc.model.Cart;
//import com.poc.model.Product;
import com.poc.service.ProductService;
import com.coviam.model.*;

@RequestMapping("/")
@Controller
public class HomeController {

	public  final Logger LOGGER=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductService productService;
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "home")
	public String home(@RequestParam("email") String email, Model model,
					   HttpSession session) {
		String viewName = "home";
		if (Utils.isValidEmailAddress(email)) {
			model.addAttribute("msg",
					"Please enter valid email address");
		} else {
			session.setAttribute("email", email);
			List<Cart> cart=null;
			try {
				cart = productService.getCartDetails(Utils.getEmail(session));
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				model.addAttribute("msg","Sorry || some error has ocuured");
				return "errorPage";
			}
			session.setAttribute("cartList", cart);
			viewName = "userProfile";
		}
		return viewName;

	}

	@RequestMapping(value = "userProfile")
	public String userProfile(@RequestParam("submit") String submit, Model model) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("viewProductOption", "viewProductOption");
		map.put("View Cart", "viewCart");
		map.put("View Previous Order", "orderDetails");
		return map.get(submit);
	}

	@RequestMapping(value = "productDetails")
	public String productDetails(@RequestParam("pid") Integer id,@RequestParam("submit") String submit,
								 @RequestParam("mid") Integer mid, Model model) {
		LOGGER.debug("Id of product :{}",id);
		LOGGER.debug("Product Id : {}", submit);
		Product product = productService.getProductByProductIdAndMerchantID(id,mid);
		if("Add to Cart".equals(submit)){
			LOGGER.debug("Adding in cart1 : product id: {}", id);
			LOGGER.debug("Got product1 {}", product);
			model.addAttribute("product", product);
			return "productDetails";
		}
		if(product.getProductId().equals(submit)){
			model=getModelForViewProductForASingleProduct(model,submit);
			LOGGER.debug("Calling view product page and productId is {}", submit);
			return "viewProducts";
		}
		if (product.getMerchantName().equals(submit)){
			LOGGER.debug("Getting merchant for merchant Id : {}", product.getMerchantId());
			Merchant merchant=productService.getMerchantInfo(product.getMerchantId());
			model.addAttribute("merchant",merchant);
			LOGGER.debug("Merchant details : {}",merchant);
			return "merchantInfoPage";
		}
		return "";
	}

	@RequestMapping(value = "addToCart")
	public String addToCart(@RequestParam("id") Integer id,
							@RequestParam("quantity") int quantity,@RequestParam("mid") Integer mid, Model model,
							HttpSession session) {

		String viewName = "viewProducts";
		LOGGER.debug("Adding in cart2 : product id: {}" ,id);
		//Product product = productService.getProductByProductId(id);
		if (Utils.isPresentInCart(id, session)) {
			LOGGER.debug("Present in cart : {}", Utils.isPresentInCart(id, session));
			viewName = "addToCartErrorPage";
		} else {
			model = getModelForViewProduct(model);
			Product product = productService.getProductByProductIdAndMerchantID(id,mid);
			LOGGER.debug("Got product {}" ,product);
			Cart cart = new Cart(product, Utils.getEmail(session), quantity);
			LOGGER.debug("Cart in controller : {} ",cart);
			cart=productService.addToCart(cart);
			LOGGER.debug("Cart in controller after updating in database: {} ",cart);
			List<Cart> cartList = Utils.getCartList(session);
			cartList.add(cart);
		}
		return viewName;
	}

	@RequestMapping(value = "viewCart")
	public String viewCart(Model model, @RequestParam("submit") String submit,
						   HttpSession session) {
		if ("Product List".equals(submit))
			model = getModelForViewProduct(model);
		Map<String, String> map = new HashMap<String, String>();
		map.put("View Cart", "viewCart");
		map.put("Product List", "viewProducts");
		map.put("Search Product", "viewProductOption");
		return map.get(submit);
	}

	@RequestMapping(value = "removeFromCart")
	public String removeFromCart(@RequestParam("cartId") int cartId,
								 Model model, HttpSession session) {
		List<Cart> cartList = Utils.getCartList(session);
		productService.removeCartById(cartId);
		try {
			cartList = productService.getCartDetails(Utils.getEmail(session));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			model.addAttribute("msg","Sorry || some error has ocuured");
			return "errorPage";
		}
		session.setAttribute("cartList", cartList);
		return "viewCart";
	}

	@RequestMapping(value = "clearCart")
	public String clearCart(Model model, HttpSession session) {
		List<Cart> cartList = Utils.getCartList(session);
		productService.removeCartByUserEmail(Utils.getEmail(session));
		cartList.clear();
		model = getModelForViewProduct(model);
		return "viewProducts";
	}

	@RequestMapping(value = "updateCartItemQuantity")
	public String updateCartItemQuantity(@RequestParam("cartId") int cartId,
										 Model model) {
		model.addAttribute("cartId", cartId);
		return "updateCartItemQuantity";
	}

	@RequestMapping(value = "updateCart")
	public String updateCart(
			@RequestParam("productQunatity") int productQunatity,
			@RequestParam("cartId") int cartId, HttpSession session,Model model) {
		productService.updateProductQuantityById(productQunatity, cartId);
		List<Cart> cart=null;
		try {
			cart = productService.getCartDetails(Utils.getEmail(session));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			model.addAttribute("msg","Sorry || some error has ocuured");
			return "errorPage";
		}
		session.setAttribute("cartList", cart);
		return "viewCart";
	}

	public Model getModelForViewProduct(Model model) {
		String url = Constants.PRODUCT_URL + "/getAllProducts?page={page}";
		Page<Product> productsPage = productService.getProductsByPage(0);
		List<Product> products = productsPage.getContent();
		//int totalPages = productsPage.getTotalPages();
		model.addAttribute("totalPages", Constants.TOTAL_PAGES);
		model.addAttribute("products", products);
		return model;
	}
	public Model getModelForViewProductForASingleProduct(Model model,String productId) {
		//String url = Constants.PRODUCT_URL + "/getProduct?proId={productId}&page={page}";
		Page<Product> productsPage = productService.getAllSameProduct(productId,0);
		List<Product> products = productsPage.getContent();
		int totalPages = productsPage.getTotalPages();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("products", products);
		return model;
	}
}