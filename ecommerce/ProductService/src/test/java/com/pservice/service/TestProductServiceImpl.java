package com.pservice.service;

//import com.packing.model.Product;
//import com.packing.model.ProductMerchant;

import com.coviam.model.Merchant;
import com.coviam.model.Product;
import com.coviam.model.ProductMerchant;
import com.coviam.model.ProductTable;
import com.pservice.dao.ProductDao;
import com.pservice.dao.ProductMerchantRepo;
import com.pservice.dao.ProductRepo;
import com.pservice.utilities.Utils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


import java.util.List;

/**
 * Created by coviam on 17/08/17.
 */
public class TestProductServiceImpl {


    @Mock
    ProductMerchantRepo productMerchantRepo;

    @Mock
    ProductDao productDao;

    @Mock
    ProductRepo productRepo;

    @InjectMocks
    ProductServiceImpl productService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetProductByIdAndMerchantId() throws Exception {

        ProductMerchant productMerchant = new ProductMerchant("Tel001", 2, 500, 200.0, 4.5);
        when(productMerchantRepo.findProductMerchantByProductIdAndAndMerchantId(any(String.class), any(Integer.class))).thenReturn(productMerchant);

        Merchant merchant = new Merchant(2, new Float(7.0), "Merchant", "abc@xyz.com");
        when(productDao.getMerchant(any(Integer.class))).thenReturn(merchant);

        //    public ProductTable(String productId, String productName, String productDescription, String productCategory) {

        ProductTable productTable = new ProductTable("Tel001","LG 1","42 Inch","Electronics");
        productTable.setId(1);
        when(productRepo.findById(any(Integer.class))).thenReturn(productTable);

        Product product=new Product();

        //when(Utils.setProduct(any(ProductTable.class), any(ProductMerchant.class), any(Merchant.class))).thenReturn(product);

        Product returnProduct = productService.getProductByIdAndMerchantId(1, 2);

        assertEquals("Tel001", returnProduct.getProductId());

        assertEquals("Merchant", returnProduct.getMerchantName());

    }


}
