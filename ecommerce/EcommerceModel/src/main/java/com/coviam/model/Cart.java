package com.coviam.model;

import javax.persistence.*;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Integer id;

	@Column(nullable = false)
	private String userEmail;

	@Column(nullable = false)
	private Integer productId;

	@Column(nullable = false)
	private Integer productQuantity;

	@Column(nullable = false)
	private Double productPrice;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private Integer merchantId;

	public Cart() {
	}

	public Cart(Product product, String userEmail, Integer quantity) {
		this.userEmail=userEmail;
		this.productId=product.getId();
		this.productQuantity=quantity;
		this.productPrice=product.getProductPrice();
		this.productName=product.getProductName();
		this.merchantId=product.getMerchantId();
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	@Override
	public String toString() {
		return "Cart{" +
				"id=" + id +
				", userEmail='" + userEmail + '\'' +
				", productId=" + productId +
				", productQuantity=" + productQuantity +
				", productPrice=" + productPrice +
				", productName='" + productName + '\'' +
				", merchantId=" + merchantId +
				'}';
	}
}
