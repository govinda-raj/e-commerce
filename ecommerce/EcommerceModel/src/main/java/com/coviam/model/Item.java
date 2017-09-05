package com.coviam.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by coviam on 29/07/17.
 */
@Entity
@Table(name="orderDetails")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;

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

    public Item(){}

    public Item(Integer productId, Integer productQuantity, Double productPrice, String productName, Integer merchantId) {
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productName = productName;
        this.merchantId = merchantId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", productId=" + productId +
                ", productQuantity=" + productQuantity +
                ", productPrice=" + productPrice +
                ", productName='" + productName + '\'' +
                ", merchantId=" + merchantId +
                '}';
    }
}
