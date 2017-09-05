package com.coviam.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by coviam on 14/08/17.
 */


@Entity
@Table(name = "product")
public class ProductTable implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    @Column(name = "productId", nullable = false,unique = true)
    private String productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productDescription;

    @Column(nullable = false)
    private String productCategory;

    @OneToMany(cascade=CascadeType.ALL ,fetch=FetchType.EAGER)
    private Set<ProductMerchant> productMerchantSet;

    public ProductTable() {
    }

    public ProductTable(String productId, String productName, String productDescription, String productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
    }



    public Set<ProductMerchant> getMerchants() {
        return productMerchantSet;
    }

    public void setMerchants(Set<ProductMerchant> productMerchantSet) {
        this.productMerchantSet = productMerchantSet;
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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }


    @Override
    public String toString() {
        return "ProductTemp{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productMerchantSet=" + productMerchantSet +
                '}';
    }
}
