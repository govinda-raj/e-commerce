package com.coviam.model;

import java.util.Set;

public class Product {

	private Integer id;
	private String productId;
	private String productName;
	private String productDescription;
	private Integer productStock;
	private String productCategory;
	private Double productPrice;
	private String merchantName;
	private Integer merchantId;


	public Product() {
	}

	public Product(Integer id, String productId, String productName, String productDescription, Integer productStock, String productCategory, Double productPrice, String merchantName, Integer merchantId) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productStock = productStock;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.merchantName = merchantName;
		this.merchantId = merchantId;
	}

	public Product(Integer id, String productId, String productName, String productDescription, String productCategory) {
		this.id=id;
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productCategory = productCategory;
	}


	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", productId='" + productId + '\'' +
				", productName='" + productName + '\'' +
				", productDescription='" + productDescription + '\'' +
				", productStock=" + productStock +
				", productCategory='" + productCategory + '\'' +
				", productPrice=" + productPrice +
				", merchantName='" + merchantName + '\'' +
				", merchantId='" + merchantId + '\'' +
				'}';
	}

}
