package com.poc;

public class Constants {
	public static final String CART_URL="http://localhost:8383/cartApi";
	public static final String PRODUCT_URL="http://localhost:8181/productApi";
	public static final String  ORDER_URL="http://localhost:8484/order";
    public static final String MERCHANT_URL="http://localhost:8585/merchantApi";
	public static final String ADD_PRODUCT= "/addProduct";
	
    public static final String CHECK_AVAILABILTY_OF_PRODUCT= "/checkAvail";
    public static final String SEARCH_BY_NAME ="/searchByName?searchName={name}";
    public static final String SEARCH_BY_CATEGORY="/searchByCategory?searchByCategory={category}";
    public static final String REDUCE_QUANTITY ="/reduceQuantity?productId={productId}&quantity={quantity}&merchatId={merchantId}";
    public static final String GET_ALL_PRODUCTS="/getAllProducts?page={pageNumber}";
    public static final String GET_CATEGORIES ="/getCategories";
    public static final String GET_PRODUCT_BY_ID_AND_Merchant_ID="/getProductById?productId={productId}&merchantId={merchantId}";
    public static final String GET_MERCHANT="/getMerchantById?id={id}";
    public static final String GET_PRODUCT="/getProduct?proId={productId}&page={page}";


    public static final String ADD_CART= "/addToCart";
    public static final String REMOVE_FROM_CART_BY_USEREMAIL_PRODUCTID="/removeFromCartByUserEmailProductId?userEmail={userEmail}&productId={productId}";
    public static final String GET_CART_BY_USEREMAIL="/getCartByUserEmail?userEmail={userEamil}";
    public static final String REMOVE_CART_BY_USEREMAIL="/removeCartByUserEmail?userEmail={userEamil}";
    public static final String UPDATE_PRODUCT_QUANTITY_BY_ID="/updateProductQuantityById?productQuantity={productQuantity}&cartId={id}";
    public static final String REMOVE_CART_BY_ID="/removeCartById?id={id}";

    public static final String GET_ORDER_DETAILS_BY_USEREMAIL="/getOrderDetailsByUserEmail?userEmail={userEamil}";
    public static final String PLACE_ORDER="/placeOrder";

    public static final String GET_MERCHANT_EMAIL="/getMerchantEmail?merchantId={merchantId}";
    public static final String GET_MERCHANT_INFO="/getMerchantByMerchantId?merchantId={merchantId}";

    public static final String GET_MERCHANT_BY_EMAIL="/getMerchantByEmail?merchantId={merchantEmail}";

    public static final Integer TOTAL_PAGES=24;

    public static final Double STOCK_WEIGHTAGE=0.3;
    public static final Double MERCHANT_RATING_WEIGHTAGE=0.7;
}
