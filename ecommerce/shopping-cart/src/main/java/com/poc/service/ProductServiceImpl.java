package com.poc.service;

import java.io.IOException;
import java.util.*;

//import com.poc.model.*;
//import com.poc.model.Product;
import com.poc.utilities.Mail;

import com.poc.utilities.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.dao.PocRepository;
import com.poc.utilities.CustomPageImpl;

import com.coviam.model.*;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private PocRepository repository;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public List<Product> getAllProducts(String url, String key) {
		RestTemplate rt = new RestTemplate();
		String str = rt.getForObject(url, String.class, key);
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Product>> refType = new TypeReference<List<Product>>() {
		};
		List<Product> products = null;
		try {
			products = mapper.readValue(str, refType);
		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return products;
	}

	public List<Cart> getCartDetails(String userEmail) throws Exception {
		List<Cart> cartList = null;
		String cartJson = repository.getCartDetails(userEmail);
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Cart>> refType = new TypeReference<List<Cart>>() {
		};
		try {
			cartList = mapper.readValue(cartJson, refType);
		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return cartList;
	}

	@Override
	public List<String> getCategories() {
		RestTemplate rt = new RestTemplate();
		String categoriesJson = repository.getCategories();
		ObjectMapper mapper = new ObjectMapper();
		List<String> categories = null;
		TypeReference<List<String>> refType = new TypeReference<List<String>>() {
		};
		try {
			categories = mapper.readValue(categoriesJson, refType);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return categories;
	}

	public Page<Product> getProductsByPage(int pageNumber) {
		String productsJson = repository.getAllProducts(pageNumber);
		LOGGER.debug("Fetched product: {}", productsJson);
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<CustomPageImpl<Product>> refType = new TypeReference<CustomPageImpl<Product>>() {
		};
		Page<Product> products = null;
		try {
			products = mapper.readValue(productsJson, refType);
		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return products;
	}

	public Page<Product> getAllSameProduct(String productId, int pageNumber) {
		String productsJson = repository.getAllSameProduct(productId, pageNumber);
		LOGGER.debug("ProductJSon ; {}", productsJson);
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<CustomPageImpl<Product>> refType = new TypeReference<CustomPageImpl<Product>>() {
		};
		Page<Product> products = null;
		try {
			products = mapper.readValue(productsJson, refType);
			LOGGER.debug("Page of products : {}", products);
		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return products;
	}


	@Override
	public Product getProductByProductIdAndMerchantID(Integer productId,Integer merchantId){
		return repository.getProductByProductIdAndMerchantId(productId,merchantId);
	}

	@Override
	public Cart addToCart(Cart cart) {
		cart = repository.addToCart(cart);
		LOGGER.debug("Cart while adding : {} ",cart);
		return cart;
	}

	@Override
	public void removeCartById(int cartId) {
		repository.removeCartById(cartId);
	}

	@Override
	public boolean updateProductQuantityById(int productQunatity, int cartId) {
		repository.updateProductQuantityById(productQunatity, cartId);
		return false;
	}

	@Override
	public void removeCartByUserEmail(String userEmail) {
		repository.removeCartByUserEmail(userEmail);
	}

	@Override
	public ProductTable addProduct(ProductTable product) {
		product = repository.addProduct(product);
		return product;
	}

	@Override
	public Order placeOrder(List<Cart> cartList, String userEmail) {
		Order order = new Order();
		order.setUserEmail(userEmail);
		Set<Item> items = new HashSet<Item>();
		for (Cart cart : cartList) {
			Item item = new Item();
			item.setProductId(cart.getId());
			item.setProductName(cart.getProductName());
			item.setProductPrice(cart.getProductPrice());
			item.setProductQuantity(cart.getProductQuantity());
			item.setMerchantId(cart.getMerchantId());
			items.add(item);
		}
		order.setItems(items);
		order = repository.placeOrder(order);
		return order;
	}

	@Override
	public void reduceQuantity(List<Cart> cartList) {
		repository.reduceQuantity(cartList);
	}

	@Override
	public void mailToMerchants(Order order) {
		Set<Item> orderedItem = order.getItems();
		Map<Integer, List<Item>> itemListMap = new HashMap<Integer, List<Item>>();
		Map<Integer, String> emailMap = new HashMap<Integer, String>();
		for (Item itemset : orderedItem) {
				if (itemListMap.get(itemset.getMerchantId()) == null) {
					List<Item> itemList = new ArrayList<Item>();
					itemList.add(itemset);
					itemListMap.put(itemset.getMerchantId(), itemList);
					String email = repository.getMerchantEmail(itemset.getMerchantId());
					if (email != null)
						emailMap.put(itemset.getMerchantId(), email);
					else
						LOGGER.debug("Email is null");
				} else {
					itemListMap.get(itemset.getMerchantId()).add(itemset);
				}
			}
		for (Map.Entry<Integer, List<Item>> entry : itemListMap.entrySet()) {
			LOGGER.debug("Email : {} ", emailMap.get(entry.getKey()));
			LOGGER.debug("List of items : {} ", entry.getValue());
			Mail.sendMailToMerchant(emailMap.get(entry.getKey()), entry.getValue());
		}


	}

	public Merchant getMerchantInfo(Integer merchantId) {
		Merchant merchant = repository.getMerchant(merchantId);
		if (merchant == null) {
			//merchant = new Merchant("govind.raj@coviam.com", 5, "Merchant6");
			LOGGER.debug("Merchant : {}", merchant);
		}
		return merchant;
	}

	public Merchant getMerchantByEmail(String merchantEmail){
		Merchant merchant = repository.getMerchantByEmail(merchantEmail);
		if (merchant == null) {
			//merchant = new Merchant("govind.raj@coviam.com", 5, "Merchant6");
			LOGGER.debug("Getting Merchant by email : {}", merchant);
		}
		return merchant;
	}
}