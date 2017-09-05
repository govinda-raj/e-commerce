package com.coviam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by coviam on 09/08/17.
 */
@Entity
@Table(name = "merchant")
public class Merchant implements Serializable{

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "merchantId")
    private Integer merchantId;

    private Float rating;

    private String merchantName;

    @Column(nullable = false, unique = true)
    private String emailId;

    public Merchant() {
    }

    public Merchant(float rating, String merchantName, String emailId) {
        this.rating = rating;
        this.merchantName = merchantName;
        this.emailId=emailId;
    }

    public Merchant(Integer merchantId, Float rating, String merchantName, String emailId) {
        this.merchantId = merchantId;
        this.rating = rating;
        this.merchantName = merchantName;
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantId=" + merchantId +
                ", rating=" + rating +
                ", merchantName='" + merchantName + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
