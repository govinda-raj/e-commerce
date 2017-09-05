package com.merchant.service;

import com.merchant.dao.MerchantDao;
import com.merchant.dao.MerchantRepository;
import com.coviam.model.Merchant;
import com.coviam.model.ProductTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by coviam on 09/08/17.
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    private final static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MerchantServiceImpl.class);


    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private MerchantRepository merchantRepository;

    public Merchant getMerchant(Integer merchantId){
        return merchantDao.getMerchantByMerchantId(merchantId);
    }

    public Float getRating(Integer merchantId){
        Merchant merchant=merchantDao.getMerchantByMerchantId(merchantId);

        return merchant.getRating();
    }
    public String getAllProducts(Integer merchantId){
        return merchantRepository.getProducts(merchantId);
    }

    public void addMerchant(Merchant merchant){
        merchantDao.save(merchant);
    }

    public String getMerchantNameById(Integer merchantId){
        Merchant merchant=merchantDao.getMerchantByMerchantId(merchantId);
        if(merchant!=null) {
            LOGGER.debug("Merchant : {} ",merchant);
            return merchant.getMerchantName();
        }
        else{
            return null;
        }
    }

    public Boolean updateRating(Float rating, Integer merchantId){
        merchantDao.updateRating(rating,merchantId);
        merchantRepository.updateFactor(rating,merchantId);
        return true;
    }

    public ProductTable addProduct(ProductTable productTable){
        LOGGER.debug("Adding ProductTable : {}", productTable);
        merchantRepository.addProduct(productTable);
        return productTable;
    }

    @Override
    public Merchant getMerchantByEmail(String emailId) {
        LOGGER.debug("Getting merchant for merchantEmail : {}", emailId);
        Merchant merchant=merchantDao.getMerchantByEmailId(emailId);
        LOGGER.debug("Getting merchant by merchantEmail : {}", merchant);
        return merchant;
    }


}
