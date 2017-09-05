package com.merchant.dao;

import com.coviam.model.ProductTable;

/**
 * Created by coviam on 09/08/17.
 */

public interface MerchantRepository {

    public String getProducts(Integer merchantId) ;

    public ProductTable addProduct(ProductTable productTable);

    public Boolean updateFactor(Float rating,Integer merchantId);
}
