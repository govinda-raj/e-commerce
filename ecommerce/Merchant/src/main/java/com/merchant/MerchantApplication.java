package com.merchant;

import com.coviam.model.Merchant;
import com.coviam.model.ProductMerchant;
import com.coviam.model.ProductTable;
import com.github.javafaker.Faker;
import com.merchant.service.MerchantService;
import com.merchant.utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

//import com.merchant.model.Merchant;
//import com.merchant.model.ProductTable;
//import com.merchant.model.ProductMerchant;

/**
 * Created by coviam on 09/08/17.
 */
//@EnableJpaRepositories("com.coviam.model.*")
@ComponentScan(basePackageClasses = { com.coviam.model.Merchant.class })
@EntityScan(basePackageClasses = { com.coviam.model.Merchant.class })
@ComponentScan(basePackages = {"com.merchant"})
@SpringBootApplication
public class MerchantApplication implements CommandLineRunner{

    @Autowired(required = true)
    private MerchantService merchantService;

    public static void main(String[] args) {
        SpringApplication.run(MerchantApplication.class,args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        Merchant merchant2=new Merchant(5,"Merchant2","govind.raj@coviam.com");
//        Merchant merchant3=new Merchant(3,"Merchant3","govind.raj@coviam.com");
//        Merchant merchant4=new Merchant(4,"Merchant4","govind.raj@coviam.com");
//        merchantService.addMerchant(new Merchant(4,"Merchant1","govind.raj@coviam.com"));
//        merchantService.addMerchant(merchant2);
//        merchantService.addMerchant(merchant3);
//        merchantService.addMerchant(merchant4);
        //GenerateUniqueName generateUniqueName=new GenerateUniqueName();


        int merchantId;
        int productStock;
        Double productPrice;
        for(int i=0;i<2;i++) {
            float r = (float) (Math.random() * (10 - 0)) + 0;
            Faker faker = new Faker();
            for (int j=0;j<10;j++) {
                String productId = Utils.generateRandomString();
                ProductTable productTableTable = new ProductTable(productId, faker.name().lastName(), faker.name().name(), faker.name().firstName());
                Set<ProductMerchant> productMerchants3 = new HashSet<>();
                for (int k=0;k<3;k++) {
                    productStock = (int) (Math.random() * (1000 - 0)) + 0;
                    productPrice = (Double) (Math.random() * (50000 - 1000)) + 1000;
                    merchantId = (int) (Math.random() * (2 - 0)) + 0;
                    productPrice = BigDecimal.valueOf(productPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    ProductMerchant productMerchant = new ProductMerchant(productId, merchantId, productStock, productPrice, 4.0);
                    productMerchants3.add(productMerchant);
                }
                productTableTable.setMerchants(productMerchants3);
                merchantService.addProduct(productTableTable);
            }
            merchantService.addMerchant(new Merchant(i,r,faker.name().firstName(),faker.name().firstName()+"@gmail.com"));

        }
            //productRepo.save(productTable3);
//        Faker faker=new Faker();
//        for(int i=0;i<5000;i++){
//            float r = (float) (Math.random() * (10 - 0)) + 0;
//            merchantService.addMerchant(new Merchant(r,faker.name().firstName(),"govind.raj@coviam.com"));
//        }
//
//        ProductTable productTableTable2 = new ProductTable(productId, faker.name().lastName(), faker.name().name(), faker.name().firstName());
//        ProductTable productTableTable3 = new ProductTable(productId, faker.name().lastName(), faker.name().name(), faker.name().firstName());
//        ProductTable productTableTable4 = new ProductTable(productId, faker.name().lastName(), faker.name().name(), faker.name().firstName());
//        ProductTable productTableTable5 = new ProductTable(productId, faker.name().lastName(), faker.name().name(), faker.name().firstName());
//        ProductTable productTableTable6 = new ProductTable(productId, faker.name().lastName(), faker.name().name(), faker.name().firstName());
//        ProductTable productTableTable7 = new ProductTable(productId, faker.name().lastName(), faker.name().name(), faker.name().firstName());
//        ProductTable productTableTable8 = new ProductTable(productId, faker.name().lastName(), faker.name().name(), faker.name().firstName());
//        ProductTable productTableTable9 = new ProductTable(productId, faker.name().lastName(), faker.name().name(), faker.name().firstName());
//        ProductTable productTableTable10 = new ProductTable(productId, faker.name().lastName(), faker.name().name(), faker.name().firstName());
//        ProductMerchant productMerchant1 = new ProductMerchant(productId, merchantId, productStock, productPrice, 3.0);
//        ProductMerchant productMerchant2 = new ProductMerchant(productId, merchantId, productStock, productPrice, 5.0);


    }
}
