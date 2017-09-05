package com.pservice;

/**
 * Created by coviam on 02/08/17.
 */
public class Constants {

    public static final String MERCHANT_URL="http://localhost:8585/merchantApi";

    public static final String UPDATE_FACTOR="/updateFactor";

    public static final String ADD_PRODUCT= "/addProduct";
    public static final String CHECK_AVAILABILTY_OF_PRODUCT= "/checkAvail";
    public static final String SEARCH_BY_NAME ="/searchByName";
    public static final String SEARCH_BY_CATEGORY="/searchByCategory";
    public static final String REDUCE_QUANTITY ="/reduceQuantity";
    public static final String GET_ALL_PRODUCTS="/getAllProducts";
    public static final String GET_CATEGORIES ="/getCategories";
    public static final String GET_PRODUCT_BY_ID_AND_Merchant_ID="/getProductById";
    public static final String GET_PRODUCT ="/getProduct";
    public static final Integer pageSize=20;

    public static final String GET_MERCHANT="/getMerchantByMerchantId?merchantId={merchantId}";

    //http://localhost:8585/merchantApi/getMerchantByMerchantId?merchantId=1

    public static final String GET_RATING="/getRating?merchantId={merchantId}";
    public static final String GET_MERCHANT_NAME="/getMerchantName?merchantId={merchantId}";


    public static final Double STOCK_WEIGHTAGE=0.3;
    public static final Double MERCHANT_RATING_WEIGHTAGE=0.7;



    public static final String PRODUCT_URL="http://localhost:8181/productApi";


    public static final String GET_PRODUCT_BY_ID_AND_MERCHANT_ID_TEST="/getProductById?productId={productId}&merchantId={merchantId}";

}