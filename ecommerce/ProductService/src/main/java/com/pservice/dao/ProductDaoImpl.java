package com.pservice.dao;

import com.pservice.Constants;
import com.pservice.controller.ProductController;
//import com.pservice.model.Merchant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.coviam.model.*;


/**
 * Created by coviam on 10/08/17.
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);


    public Merchant getMerchant(Integer merchantId){
        RestTemplate restTemplate=new RestTemplate();
        Merchant merchant=null;
        LOGGER.debug("Getting Merchant from merchant microservice: for merchant Id {} ", merchantId);
        merchant= restTemplate.getForObject(Constants.MERCHANT_URL+Constants.GET_MERCHANT,Merchant.class,merchantId);
        LOGGER.debug("Merchant from merchant microservice: {} ", merchant);
        return merchant;
    }
}
