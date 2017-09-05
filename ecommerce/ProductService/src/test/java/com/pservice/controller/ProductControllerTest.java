package com.pservice.controller;

import com.coviam.model.Product;
import com.coviam.model.ProductMerchant;
import com.pservice.Constants;
import com.pservice.Utilities.TestUtils;
import com.pservice.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by coviam on 18/08/17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;


    @Test
    public void testGetProductbyid() throws Exception{
        //prepare data and mocks behaviour
        Product product=new Product(1,"Tel001","Iphone6","2 GB RAM",200,"Mobile",35000.0,"Merchant",1);
        when(productService.getProductByIdAndMerchantId(any(Integer.class),any(Integer.class))).thenReturn(product);

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(Constants.PRODUCT_URL + Constants.GET_PRODUCT_BY_ID_AND_MERCHANT_ID_TEST, new Integer(1), new Integer(1)).accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        // verify
        int status = result.getResponse().getStatus();
//        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        // verify that service method was called once
        verify(productService).getProductByIdAndMerchantId(any(Integer.class),any(Integer.class));

        Product resultProduct = TestUtils.jsonToObject(result.getResponse().getContentAsString());
        assertNotNull(resultProduct);
        assertEquals(1, resultProduct.getId().intValue());
    }
}
