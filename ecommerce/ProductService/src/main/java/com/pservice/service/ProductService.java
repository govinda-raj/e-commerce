package com.pservice.service;

//import com.pservice.model.Product;
//import com.pservice.model.ProductMerchant;
//import com.pservice.model.ProductTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by coviam on 25/07/17.
 */


import com.coviam.model.*;

public interface ProductService {


    /**
     * get all products by page with size Constants.pageSize and it will return a page of given size
     * @param pageable
     * @return page

     */

    public Page<Product> getAllProducts(Pageable pageable);

    /**
     *
     * Save new productTable in database
     * @param productTable
     * @return
     */

    public ProductTable addProduct(ProductTable productTable);


    /**
     *
     *  Search for product by name
     * @param productName
     * @return ListOf products
     */

    public List<Product> searchByName(String productName,Pageable pageable) ;
    /**
     *
     *  Search for product by category
     * @param category
     * @return ListOf products
     */

    public List<Product> searchByCategory(String category,Pageable pageable) ;

    /**
     *
     *  Decrease the quantity of product
     * @param productId
     * @return
     */

    public Boolean reduceQuantity(String productId, Integer quant, Integer merchantId);

    /**
     *
     *  Get all categories available
     * @param
     * @return status
     */
    public List<String> getCategories();

    /**
     *
     *  Get ProductTable by productID
     * @param
     * @return status
     */
    public Product getProductByIdAndMerchantId(Integer id,Integer merchantId) ;

    //public void reduceQuantityTest(Map<String, Integer> orderedProductList);

//    public List<ProductTable> findProductsByMerchant(Integer merchantId);
    public Page<Product> findProductByProductId(String productId, Pageable pageable);

    //public Integer findMerchantById(Integer id);


    public void updateFactor(Float rating, Integer merchantId);

        public ProductMerchant getProductMerchantByProductIdAndMerchantId(String productId, Integer merchantId);

    public List<ProductMerchant> findProductMerchantsByProductIdOrderByFactor(String productId);
    public ProductMerchant findProductMerchantByProductIdOrderByFactor(String productId);

    }
