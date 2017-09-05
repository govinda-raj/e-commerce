package com.pservice.Utilities;

import com.coviam.model.Product;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Created by coviam on 18/08/17.
 */
public class TestUtils {



    public static Product jsonToObject(String jsonResult){
        Product product=new Product();
        // T content;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Product> typeReference=new TypeReference<Product>() {};
        try {
            product=mapper.readValue(jsonResult,typeReference);
        }
        catch (Exception exception){

        }
        return product;
    }

}
