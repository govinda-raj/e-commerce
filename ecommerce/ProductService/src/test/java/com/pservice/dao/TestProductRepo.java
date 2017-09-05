package com.pservice.dao;

import com.coviam.model.Product;
import com.coviam.model.ProductTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * Created by coviam on 17/08/17.
 */

@RunWith(SpringRunner.class)
//@DataJpaTest
public class TestProductRepo {


    @Autowired
    private ProductRepo productRepo;


    @Test
    @Transactional
    @Rollback(true)
    public void should_find_ProductTable_by_id() throws Exception{
        ProductTable productTable = new ProductTable("Tel001","LG 1","42 Inch","Electronics");
        productRepo.save(productTable);

        ProductTable foundProductTable = productRepo.findOne(productTable.getId());

        Assert.assertSame(foundProductTable,productTable);
    }


}
