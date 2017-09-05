package com.merchant.service;

import com.coviam.model.Merchant;
import com.coviam.model.ProductTable;

/**
 * Created by coviam on 09/08/17.
 */


public interface MerchantService {

    public Merchant getMerchant(Integer merchantId);
    public String getAllProducts(Integer merchantId);
    public void addMerchant(Merchant merchant);

    public Float getRating(Integer merchantId);

    public String getMerchantNameById(Integer merchantId);

    public Boolean updateRating(Float rating, Integer merchantId);

    public ProductTable addProduct(ProductTable productTable);

    public Merchant getMerchantByEmail(String emailId);

    }
