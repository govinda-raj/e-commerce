package com.merchant.controller;

import com.merchant.Constants;
//import com.merchant.model.Merchant;
import com.merchant.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coviam.model.Merchant;
/**
 * Created by coviam on 09/08/17.
 */
@RestController
@RequestMapping(value = "/merchantApi")
public class MerchantController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);


    @Autowired
    private MerchantService merchantService;



    @RequestMapping(value = Constants.GET_MERCHANT_EMAIL, method = RequestMethod.GET)
    public ResponseEntity<String> getMerchantEmail(@RequestParam(value = "merchantId") Integer merchantId ){
        String returnedEmail=merchantService.getMerchant(merchantId).getEmailId();
        HttpStatus httpStatus=HttpStatus.OK;
        if(returnedEmail==null) {
            httpStatus = HttpStatus.NO_CONTENT;
            LOGGER.debug("No merchant for merchant Id :{}",merchantId);
        }
        return new ResponseEntity<String>(returnedEmail,httpStatus);
    }

    @RequestMapping(value = Constants.GET_RATING, method = RequestMethod.GET)
    public ResponseEntity<Float> getRating(@RequestParam(value = "merchantId") Integer merchantId ){
        LOGGER.debug("Merchant Id : {} ",merchantId);
        Float merchantRating=merchantService.getRating(merchantId);
        HttpStatus httpStatus=HttpStatus.OK;
        if(merchantRating==null) {
            httpStatus = HttpStatus.NO_CONTENT;
            LOGGER.debug("No merchant for merchant Id :{}",merchantId);
        }
        return new ResponseEntity<Float>(merchantRating,httpStatus);
    }



    @RequestMapping(value = Constants.GET_MERCHANT,method = RequestMethod.GET)
    public ResponseEntity<Merchant> getMerchant(@RequestParam(value = "merchantId") Integer merchantId ){
        Merchant merchant=merchantService.getMerchant(merchantId);
        HttpStatus httpStatus=HttpStatus.FOUND;
        if(merchant==null){
            httpStatus=HttpStatus.NOT_FOUND;
            LOGGER.debug("No merchant for merchant Id :{}",merchantId);
        }
        return new ResponseEntity<Merchant>(merchant,httpStatus);
    }



    @RequestMapping(value = Constants.UPDATE_RATING, method=RequestMethod.PUT)
    public Boolean updateRating(@RequestParam("rating") Float rating, @RequestParam("merchantId") Integer merchantId){
        return merchantService.updateRating(rating,merchantId);
    }

    @RequestMapping(value = Constants.GET_MERCHANT_BY_EMAIL,method = RequestMethod.GET)
    public ResponseEntity<Merchant> getMerchantByEmail(@RequestParam(value = "merchantId") String emailId ){
        Merchant merchant=merchantService.getMerchantByEmail(emailId);
        HttpStatus httpStatus=HttpStatus.FOUND;
        if(merchant==null){
            httpStatus=HttpStatus.NO_CONTENT;
            LOGGER.debug("No merchant for email :{}",emailId);
        }
        return new ResponseEntity<Merchant>(merchant, httpStatus);

    }
    @RequestMapping(value = Constants.ADD_MERCHANT, method = RequestMethod.POST)
    public Merchant addMerchant(@RequestBody Merchant merchant){
        merchantService.addMerchant(merchant);
        return merchant;
    }

    @RequestMapping(value = Constants.GET_MERCHANT_NAME, method = RequestMethod.GET)
    public ResponseEntity<String> getMerchantName(@RequestParam(value = "merchantId") Integer merchantId ){
        return new ResponseEntity<String>(merchantService.getMerchantNameById(merchantId),HttpStatus.OK);
    }

//    @RequestMapping(value = Constants.ADD_PRODUCT ,method=RequestMethod.POST)
//    public ProductTable addProduct(@RequestBody ProductTable productTable){
//        return productTable;
//    }

    @RequestMapping("*")
    @ResponseBody
    public String fallBack(){
        return "Fall Back";
    }
}
