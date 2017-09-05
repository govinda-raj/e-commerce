package com.pservice.controller;

import com.pservice.Constants;
//import com.pservice.model.Product;
//import com.pservice.model.ProductMerchant;
//import com.pservice.model.ProductTable;
import com.pservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import com.coviam.model.*;

/**
 * Created by coviam on 25/07/17.
 */

@RestController
@RequestMapping(value = "/productApi")
public class ProductController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;


    //Adding productTable
    @RequestMapping(value = Constants.ADD_PRODUCT,method =RequestMethod.POST)
    public ResponseEntity<ProductTable> addProduct(@RequestBody ProductTable productTable){
        productTable=productService.addProduct(productTable);
        LOGGER.debug("ProductTable added"  + productTable);
        return new ResponseEntity<ProductTable>(HttpStatus.CREATED);
    }


    //Search for product by name
    @RequestMapping(value = Constants.SEARCH_BY_NAME, method = RequestMethod.GET)
   public ResponseEntity<List<Product>> seachByName(@RequestParam("searchName") String name){
        List<Product> productList = productService.searchByName(name,new PageRequest(0,Constants.pageSize));
        HttpStatus httpStatus=HttpStatus.OK;
        if(productList.isEmpty()){
            LOGGER.debug("No product found with name : " + name);
            httpStatus=HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<List<Product>>(productList,httpStatus);
   }

   //search by category
    @RequestMapping(value = Constants.SEARCH_BY_CATEGORY, method = RequestMethod.GET)
    public ResponseEntity<List<Product>> searchByCateg(@RequestParam("searchByCategory") String categoryName){
       LOGGER.debug("Searching for category : " +categoryName);
        List<Product> productTableList = productService.searchByCategory(categoryName,new PageRequest(0,Constants.pageSize));
        return new ResponseEntity<List<Product>>(productTableList,HttpStatus.OK);
    }

    //Reducing the quantiy
    @RequestMapping(value = Constants.REDUCE_QUANTITY,method=RequestMethod.PUT)
    public Boolean reduceQuant(@RequestParam("productId") String productId, @RequestParam("quantity") Integer quantity,@RequestParam("merchantId") Integer merchantId){
        LOGGER.debug("Reducing quantity for " + productId +" quantity : " +quantity );
       return  productService.reduceQuantity(productId, quantity,merchantId);
    }

    //Retrieve all products
    @RequestMapping(value=Constants.GET_ALL_PRODUCTS,method=RequestMethod.GET)
    public ResponseEntity<Page<Product>> listAllproducts(@RequestParam("page") Integer page){
        LOGGER.debug("getting product List.");
        Page<Product> products=productService.getAllProducts(new PageRequest(page,Constants.pageSize));
        HttpStatus httpStatus=HttpStatus.OK;
        if(products.getTotalElements()==0){
            httpStatus=HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<Page<Product>>(products,httpStatus);
    }


    //Get All Categories
    @RequestMapping(value = Constants.GET_CATEGORIES,method=RequestMethod.GET)
    public ResponseEntity<List<String>> getCategoriesList(){
        List<String> categoriesList=productService.getCategories();
        HttpStatus httpStatus=HttpStatus.FOUND;
        if(categoriesList.isEmpty()) {
            LOGGER.debug("No category list found");
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<List<String>>(categoriesList,httpStatus);
    }

    //Get ProductTable by ProductId
    @RequestMapping(value = Constants.GET_PRODUCT_BY_ID_AND_Merchant_ID ,method=RequestMethod.GET)
    public ResponseEntity<Product> getProductbyid(@RequestParam(value = "productId") Integer productId,@RequestParam("merchantId") Integer merchantId){
        Product product =productService.getProductByIdAndMerchantId(productId,merchantId);
        HttpStatus httpStatus=HttpStatus.FOUND;
        LOGGER.debug("Getting productTable for  id :" +productId);
        if(product ==null) {
            LOGGER.error("ProductTable not found for  ID : {}",productId );
            httpStatus=HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Product>(product,httpStatus);
    }


    @RequestMapping(value = Constants.GET_PRODUCT ,method=RequestMethod.GET)
    public ResponseEntity<Page<Product>> getProductByProductId(@RequestParam(value = "proId") String productId, @RequestParam("page") Integer page){
        Page<Product> productList=productService.findProductByProductId(productId, new PageRequest(page,Constants.pageSize));
        HttpStatus httpStatus=HttpStatus.FOUND;
        LOGGER.debug("Getting product for  id :" +productId);
        if(productList==null) {
            LOGGER.error("ProductTable not found for  ID : {}",productId );
            httpStatus=HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Page<Product>>(productList,httpStatus);
    }



    @RequestMapping(value = Constants.UPDATE_FACTOR, method = RequestMethod.GET)
    public Boolean updateFactor(@RequestParam("rating") Float rating, @RequestParam("merchantId") Integer merchantId){
        productService.updateFactor(rating,merchantId);
        return true;
    }

    @RequestMapping(value = "/getProductMerchant")
    public ResponseEntity<ProductMerchant> getProductMerchant(@RequestParam(value = "productId") String productId, @RequestParam(value = "merchantId") Integer merchantId){
        ProductMerchant productMerchant= productService.getProductMerchantByProductIdAndMerchantId(productId,merchantId);
        return new ResponseEntity<ProductMerchant>(productMerchant,HttpStatus.FOUND);
    }

    @RequestMapping(value = "/getProductMerchantsOrderByFactor")
    public ResponseEntity<List<ProductMerchant>> getProductMerchants(@RequestParam(value = "productId") String productId){
        List<ProductMerchant> productMerchant= productService.findProductMerchantsByProductIdOrderByFactor(productId);
        return new ResponseEntity<List<ProductMerchant>>(productMerchant,HttpStatus.FOUND);
    }
    @RequestMapping(value = "/getProductMerchantOrderByFactor")
    public ResponseEntity<ProductMerchant> getProductMerchant(@RequestParam(value = "productId") String productId){
        ProductMerchant productMerchant= productService.findProductMerchantByProductIdOrderByFactor(productId);
        return new ResponseEntity<ProductMerchant> (productMerchant,HttpStatus.FOUND);
    }

    @RequestMapping("*")
    @ResponseBody
    public String fallbackMethod(){
        return "fallback method";
    }


}
