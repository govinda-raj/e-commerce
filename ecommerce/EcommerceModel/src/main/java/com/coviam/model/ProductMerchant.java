package com.coviam.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by coviam on 11/08/17.
 */
@Entity
@Table(name = "product_merchant")
public class ProductMerchant implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String productId;
    private Integer merchantId;
    private Integer productStock;
    private Double productPrice;
    private Double factor;

    public ProductMerchant() {
    }

    public ProductMerchant(String productId, Integer merchantId, Integer productStock, Double productPrice, Double factor) {
        this.productId = productId;
        this.merchantId = merchantId;
        this.productStock = productStock;
        this.productPrice = productPrice;
        this.factor = factor;
    }

    public Integer getId() {
        return id;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
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

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "ProductMerchant{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", merchantId=" + merchantId +
                ", productStock=" + productStock +
                ", productPrice=" + productPrice +
                ", factor=" + factor +
                '}';
    }
}
