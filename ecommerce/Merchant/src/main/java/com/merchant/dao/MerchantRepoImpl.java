package com.merchant.dao;

import com.merchant.Constants;
import com.coviam.model.ProductTable;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by coviam on 09/08/17.
 */

@Repository
public class MerchantRepoImpl implements MerchantRepository{

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MerchantRepoImpl.class);


    public String getProducts(Integer merchantId) {
        RestTemplate restTemplate=new RestTemplate();
        String productsJson=null;
        try {
            productsJson = restTemplate.getForObject("http://localhost:8181/productApi/findProductByMerchant?merchantId={merchantId}",String.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
        return productsJson;
    }

    public ProductTable addProduct(ProductTable productTable) {
        RestTemplate restTemplate=new RestTemplate();
        productTable =restTemplate.postForObject(Constants.PRODUCT_URL + Constants.ADD_PRODUCT, productTable, ProductTable.class);
        return productTable;
    }

    public Boolean updateFactor(Float rating,Integer merchantId){
        RestTemplate restTemplate=new RestTemplate();
        Boolean status=restTemplate.getForObject(Constants.PRODUCT_URL+Constants.UPDATE_FACTOR,Boolean.class,rating,merchantId);
        return status;
    }

}
