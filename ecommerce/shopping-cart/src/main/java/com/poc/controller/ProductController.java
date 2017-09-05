package com.poc.controller;

//import com.poc.model.*;
import com.poc.utilities.Utils;
import com.poc.Constants;
import com.poc.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coviam.model.*;


import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@RequestMapping("/")
@Controller
public class ProductController {

	@Autowired
	private ProductService productService;


	public  final Logger LOGGER= LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value = "/saveProduct")
	public String addProduct(HttpServletRequest request, Model model) {
		String submit = request.getParameter("submit");
		String viewName = "home";

		if ("Add Product".equals(submit)) {
			String productName = request.getParameter("productName");
			String productDescription = request.getParameter("productDescription");
			String productPrice = request.getParameter("productPrice");
			String productQuantity = request.getParameter("productQuantity");
			String select = request.getParameter("select");
			String productId = request.getParameter("productId");
			String merchantEmail=request.getParameter("merchantEmail");
			System.out.println("productId " + productId);
			boolean status = true;
			Merchant merchant=null;
			Boolean merchantStatus=true;
			String priceMsg = null, quantityMsg = null, nameMsg = null, descriptionMsg = null, productIdMsg = null,merchantMsg=null;
			String productCategory = request.getParameter("select");

			List<String> categories = productService.getCategories();
			model.addAttribute("categories", categories);

			if (Utils.isValidProductPrice(productPrice)) {
				priceMsg = "please enter valid price!";
				status = false;
			}
			if (Utils.isValidProductQuantity(productQuantity)) {
				quantityMsg = "Please enter valid quantity";
				status = false;
			}
			if (Utils.isValidEmailAddress(merchantEmail)) {
				merchantMsg = "Please enter valid emailAddress";
				merchantStatus=false;
				status = false;
			}
			if(merchantStatus){
				LOGGER.debug("Get merchant for email :{}",merchantEmail);
				merchant=productService.getMerchantByEmail(merchantEmail);
				LOGGER.debug("Got merchant for given email :{}",merchant);
				if(merchant==null) {
					merchantMsg = "Please enter valid emailAddress which is registered";
					status = false;
				}
			}

			if (Utils.isValidProductName(productName)) {
				nameMsg = "product name should not be empty or only numbers";
				status = false;
			}

			if (Utils.isValidProductName(productDescription)) {
				descriptionMsg = "product description  should not be empty or only numbers";
				status = false;
			}

			if (productId == null || productId.equals("")) {
				productIdMsg = "productId can not be empty";
				status = false;
			}

			if (status == false) {
				model.addAttribute("priceMsg", priceMsg);
				model.addAttribute("quantityMsg", quantityMsg);
				model.addAttribute("nameMsg", nameMsg);
				model.addAttribute("descriptionMsg", descriptionMsg);
				model.addAttribute("productIdMsg", productIdMsg);
				model.addAttribute("merchantMsg",merchantMsg);

			} else {
				int productStock = Integer.parseInt(productQuantity);
				double price = Double.parseDouble(productPrice);
				ProductTable productTable=new ProductTable(productId,productName,productDescription,productCategory);
				Set<ProductMerchant> productMerchantSet=new HashSet<ProductMerchant>();
				Double factor=Utils.configFactor(merchant.getRating(),price,productStock);
				ProductMerchant productMerchant=new ProductMerchant(productId,merchant.getMerchantId(),productStock,price,factor);
				productMerchantSet.add(productMerchant);
				productTable.setMerchants(productMerchantSet);
				productTable=productService.addProduct(productTable);
				LOGGER.debug("Added product :{}",productTable);
				model.addAttribute("successMsg", "Product added successfully.");
			}
			viewName = "addProduct";
		}
		return viewName;
	}

	@RequestMapping(value = "/addProduct")
	public String addProduct(Model model) {
		List<String> categories = productService.getCategories();
		model.addAttribute("categories", categories);
		return "addProduct";
	}

	@RequestMapping(value = "/search")
	public String search(@RequestParam("submit") String submit,
			HttpSession session, Model model) {

		String viewName = "searchByName";
		if ("Search By Category".equals(submit)) {
			List<String> categories = productService.getCategories();
			model.addAttribute("categories", categories);
			viewName = "searchByCategory";
		}
		return viewName;
	}

	@RequestMapping(value = "/searchProductByCategory")
	public String searchProductByCategory(
			@RequestParam("select") String category, HttpSession session,
			Model model) {
		String url = Constants.PRODUCT_URL +Constants.SEARCH_BY_CATEGORY;
		List<Product> productList = productService
				.getAllProducts(url, category);
		model.addAttribute("products", productList);
		return "viewProducts";
	}

	@RequestMapping(value = "/searchByName")
	public String searchByName(@RequestParam("name") String name, Model model) {
		String url = Constants.PRODUCT_URL +Constants.SEARCH_BY_NAME;
		List<Product> products = productService.getAllProducts(url, name);
		model.addAttribute("products", products);
		return "viewProducts";
	}

	@RequestMapping(value = "/getProductsPage")
	public String getProductsPage(@RequestParam("submit") int pageNumber,Model model) {
		Page<Product> productsPage = productService.getProductsByPage(pageNumber);
		List<Product> products = productsPage.getContent();
		//int totalPages = productsPage.getTotalPages();
		model.addAttribute("totalPages", Constants.TOTAL_PAGES);
		model.addAttribute("products", products);
		return "viewProducts";
	}
}
