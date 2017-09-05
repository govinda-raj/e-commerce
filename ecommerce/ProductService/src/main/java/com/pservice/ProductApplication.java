package com.pservice;

import com.github.javafaker.Faker;
import com.pservice.dao.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by coviam on 25/07/17.
 * ,com.pservice.service.ProductServiceImpl.class,com.pservice.controller.ProductController.class
 */


//@EnableJpaRepositories(basePackageClasses = {com.coviam.model.ProductTable.class,com.coviam.model.ProductMerchant.class})
@ComponentScan(basePackageClasses= { com.coviam.model.ProductTable.class,com.coviam.model.ProductMerchant.class })
@EntityScan(basePackageClasses = { com.coviam.model.ProductTable.class,com.coviam.model.ProductMerchant.class })
@ComponentScan(basePackages = "com.pservice")
@SpringBootApplication
public class ProductApplication implements CommandLineRunner{

//    @Autowired
//    ProductRepo productRepo;


    public static void main(String [] args) {

        SpringApplication.run(ProductApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {


//        ProductTable productTable1 =new ProductTable("Tel001","LG Xpose1", "42 Inch Led", "Television");
//        ProductTable productTable2 =new ProductTable("Tel002","LG Xpose2", "44 Inch Led", "Television");
//        //ProductTable productTable3 =new ProductTable("Tel001","LG Xpose1", "42 Inch Led", "Television");
//        ProductTable productTable4 =new ProductTable("Tel001","LG Xpose1", "42 Inch Led",  "Television");
//        ProductTable productTable5 =new ProductTable("Tel002","LG Xpose2", "44 Inch Led", "Television");
//
//        Set<ProductMerchant> productMerchants=new HashSet<>();
////        ProductMerchant productMerchant=new ProductMerchant("Tel001",1,100,200.0,4.0);
////        ProductMerchant productMerchant1=new ProductMerchant("Tel001",4,90,300.0,3.0);
////        ProductMerchant productMerchant2=new ProductMerchant("Tel001",3,200,400.0,5.0);
////
////        productMerchants.add(productMerchant);
////        productMerchants.add(productMerchant1);
////        productMerchants.add(productMerchant2);
//        productTable1.setMerchants(productMerchants);
//
//        Set<ProductMerchant> productMerchants1=new HashSet<>();
//        ProductMerchant productMerchant3=new ProductMerchant("Tel002",1,100,200.0,4.0);
//        ProductMerchant productMerchant4=new ProductMerchant("Tel002",2,90,300.0,3.0);
//        ProductMerchant productMerchant5=new ProductMerchant("Tel002",4,200,400.0,5.0);
//        productMerchants1.add(productMerchant3);
//        productMerchants1.add(productMerchant4);
//        productMerchants1.add(productMerchant5);
//        productTable2.setMerchants(productMerchants1);
//
//        productRepo.save(productTable1);
//        productRepo.save(productTable2);

















//        int merchantId;
//        int productStock;
//        Double productPrice;
//        for(int i=0;i<50;i++){
//            merchantId = (int) (Math.random() * (1000 - 0)) + 0;
//            productStock=(int) (Math.random() * (1000 - 0)) + 0;
//            productPrice=(Double) (Math.random() * (50000 - 1000)) + 1000;
//            productPrice = BigDecimal.valueOf(productPrice)
//                    .setScale(2, RoundingMode.HALF_UP)
//                    .doubleValue();
//            Faker faker=new Faker();
//            //Utils utils=new Utils();
//            String productId=faker.name().firstName();
//            ProductTable productTable3 =new ProductTable(productId,faker.name().lastName(), faker.name().name(), faker.name().name());
//            Set<ProductMerchant> productMerchants3=new HashSet<>();
//            //productTable3.setMerchants(productMerchants3);
//            ProductMerchant productMerchant=new ProductMerchant(productId,merchantId,productStock,productPrice,4.0);
//            ProductMerchant productMerchant1=new ProductMerchant(productId,merchantId,productStock,productPrice,3.0);
//            ProductMerchant productMerchant2=new ProductMerchant(productId,merchantId,productStock,productPrice,5.0);
//            productMerchants3.add(productMerchant);
//            productMerchants3.add(productMerchant1);
//            productMerchants3.add(productMerchant2);
//            productTable3.setMerchants(productMerchants3);
//            productRepo.save(productTable3);





//            productRepo.save(new ProductTable("Tel001","LG Xpose1", "42 Inch Led", productStock, "Television", productPrice,merchantId,4.0));
//            productRepo.save(new ProductTable("Tel002","LG Xpose2", "44 Inch Led", productStock, "Television", productPrice,merchantId,3.2));
//            productRepo.save(new ProductTable("Tel003","LG Xpose3", "48 Inch Led", productStock, "Television", productPrice,merchantId,4.5));
//            productRepo.save(new ProductTable("Tel004","LG Xpose4", "50 Inch Led", productStock, "Television", productPrice,merchantId));
//            productRepo.save(new ProductTable("Tel005","LG Xpose5", "56 Inch Led", productStock, "Television", productPrice,merchantId));
//            productRepo.save(new ProductTable("Tel006","LG Xpose6", "42 Inch Led", productStock, "Television", productPrice,merchantId));
//            productRepo.save(new ProductTable("Tel007","LG Xpose7", "44 Inch Led", productStock, "Television", productPrice,merchantId));
//            productRepo.save(new ProductTable("Tel008","LG Xpose8", "48 Inch Led", productStock, "Television", productPrice,merchantId));
//            productRepo.save(new ProductTable("Tel009","LG Xpose9", "50 Inch Led", productStock, "Television", productPrice,merchantId));
//            productRepo.save(new ProductTable("Tel010","LG Xpose10", "56 Inch Led", productStock, "Television", productPrice,merchantId));//

//        }
//        for(int i=0;i<50;i++){
//            merchantId = (int) (Math.random() * (1000 - 0)) + 0;
//            productStock=(int) (Math.random() * (1000 - 0)) + 0;
//            productPrice=(Double) (Math.random() * (5000 - 500)) + 500;
//            productPrice = BigDecimal.valueOf(productPrice)
//                    .setScale(2, RoundingMode.HALF_UP)
//                    .doubleValue();
//            productRepo.save(new ProductTable("M001","LG M1", "2 GB Ram", productStock, "Mobile", productPrice,merchantId));
//            productRepo.save(new ProductTable("M002","LG M2", "1 GB Ram", productStock, "Mobile", productPrice,merchantId));
//            productRepo.save(new ProductTable("M003","LG M3", "2 GB Ram", productStock, "Mobile", productPrice,merchantId));
//            productRepo.save(new ProductTable("M004","LG M4", "4 GB Ram", productStock, "Mobile", productPrice,merchantId));
//            productRepo.save(new ProductTable("M005","LG M5", "6 GB Ram", productStock, "Mobile", productPrice,merchantId));
//            productRepo.save(new ProductTable("M006","LG M6", "2 GB Ram", productStock, "Mobile", productPrice,merchantId));
//            productRepo.save(new ProductTable("M007","LG M7", "1 GB Ram", productStock, "Mobile", productPrice,merchantId));
//            productRepo.save(new ProductTable("M008","LG M8", "2 GB Ram", productStock, "Mobile", productPrice,merchantId));
//            productRepo.save(new ProductTable("M009","LG M9", "4 GB Ram", productStock, "Mobile", productPrice,merchantId));
//            productRepo.save(new ProductTable("M010","LG M10", "6 GB Ram", productStock, "Mobile", productPrice,merchantId));
//
//        }
//        for(int i=0;i<50;i++){
//            merchantId = (int) (Math.random() * (1000 - 0)) + 0;
//            productStock=(int) (Math.random() * (1000 - 0)) + 0;
//            productPrice=(Double) (Math.random() * (500 - 100)) + 100;
//            productPrice = BigDecimal.valueOf(productPrice)
//                    .setScale(2, RoundingMode.HALF_UP)
//                    .doubleValue();
//            productRepo.save(new ProductTable("L001","Sam L1", "2 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//            productRepo.save(new ProductTable("L002","Sam L2", "1 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//            productRepo.save(new ProductTable("L003","Sam L3", "2 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//            productRepo.save(new ProductTable("L004","Sam L4", "4 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//            productRepo.save(new ProductTable("L005","Sam L5", "6 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//            productRepo.save(new ProductTable("L006","Sam L6", "2 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//            productRepo.save(new ProductTable("L007","Sam L7", "1 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//            productRepo.save(new ProductTable("L008","Sam L8", "2 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//            productRepo.save(new ProductTable("L009","Sam L9", "4 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//            productRepo.save(new ProductTable("L010","Sam L10", "6 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//
//        }
//        for(int i=0;i<50;i++){
//            merchantId = (int) (Math.random() * (1000 - 0)) + 0;
//            productStock=(int) (Math.random() * (1000 - 0)) + 0;
//            productPrice=(Double) (Math.random() * (500 - 100)) + 100;
//            productPrice = BigDecimal.valueOf(productPrice)
//                    .setScale(2, RoundingMode.HALF_UP)
//                    .doubleValue();
//            productRepo.save(new ProductTable("D001","Sam D1", "2 GB Ram and 15 inch LED", productStock, "Desktop", productPrice,merchantId));
//            productRepo.save(new ProductTable("D002","Sam D2", "1 GB Ram and 15 inch LED", productStock, "Desktop", productPrice,merchantId));
//            productRepo.save(new ProductTable("D003","Sam D3", "2 GB Ram and 15 inch LED", productStock, "Desktop", productPrice,merchantId));
//            productRepo.save(new ProductTable("D004","Sam D4", "4 GB Ram and 15 inch LED", productStock, "Laptop", productPrice,merchantId));
//            productRepo.save(new ProductTable("D005","Sam D5", "6 GB Ram and 15 inch LED", productStock, "Desktop", productPrice,merchantId));
//            productRepo.save(new ProductTable("D006","Sam D6", "2 GB Ram and 15 inch LED", productStock, "Desktop", productPrice,merchantId));
//            productRepo.save(new ProductTable("D007","Sam D7", "1 GB Ram and 15 inch LED", productStock, "Desktop", productPrice,merchantId));
//            productRepo.save(new ProductTable("D008","Sam D8", "2 GB Ram and 15 inch LED", productStock, "Desktop", productPrice,merchantId));
//            productRepo.save(new ProductTable("D009","Sam D9", "4 GB Ram and 15 inch LED", productStock, "Desktop", productPrice,merchantId));
//            productRepo.save(new ProductTable("D010","Sam D10", "6 GB Ram and 15 inch LED", productStock, "Desktop", productPrice,merchantId));
//
//        }
//        for(int i=0;i<100;i++){
//            merchantId = (int) (Math.random() * (1000 - 0)) + 0;
//            productStock=(int) (Math.random() * (1000 - 0)) + 0;
//            productPrice=(Double) (Math.random() * (500 - 100)) + 100;
//            productPrice = BigDecimal.valueOf(productPrice)
//                    .setScale(2, RoundingMode.HALF_UP)
//                    .doubleValue();
//            productRepo.save(new ProductTable("AC001","Sam AC1", "400 Watt", productStock, "AC", productPrice,merchantId));
//            productRepo.save(new ProductTable("AC002","Sam AC2", "300 Watt", productStock, "AC", productPrice,merchantId));
//            productRepo.save(new ProductTable("AC003","Sam AC3", "200 Watt", productStock, "AC", productPrice,merchantId));
//            productRepo.save(new ProductTable("AC004","Sam AC4", "500 Watt", productStock, "AC", productPrice,merchantId));
//            productRepo.save(new ProductTable("AC005","Sam AC5", "400 Watt", productStock, "AC", productPrice,merchantId));
//            productRepo.save(new ProductTable("AC006","Sam AC6", "100 Watt", productStock, "AC", productPrice,merchantId));
//            productRepo.save(new ProductTable("AC007","Sam AC7", "300 Watt", productStock, "AC", productPrice,merchantId));
//            productRepo.save(new ProductTable("AC008","Sam AC8", "500 Watt", productStock, "AC", productPrice,merchantId));
//            productRepo.save(new ProductTable("AC009","Sam AC9", "200 Watt", productStock, "AC", productPrice,merchantId));
//            productRepo.save(new ProductTable("AC010","Sam AC10", "600 Watt", productStock, "AC", productPrice,merchantId));
//
//        }




//        productRepo.save(productTable1);
//        productRepo.save(productTable2);
//        productRepo.save(productTable3);
//        productRepo.save(productTable4);
//        productRepo.save(productTable5);





    }

}
