package com.pservice.service;

import com.pservice.dao.ProductDao;
import com.pservice.dao.ProductMerchantRepo;
import com.pservice.dao.ProductRepo;
//import com.pservice.model.Merchant;
//import com.pservice.model.Product;
//import com.pservice.model.ProductMerchant;
//import com.pservice.model.ProductTable;
import com.pservice.utilities.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coviam.model.*;



import java.util.*;

/**
 * Created by coviam on 25/07/17.
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Autowired(required = true)
    private ProductRepo productRepo;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductMerchantRepo productMerchantRepo;


    @Override
    @Transactional(readOnly = false)
    public ProductTable addProduct(ProductTable productTable) {
        ProductTable returnedProductTable = productRepo.findById(productTable.getId());
        LOGGER.debug("ProductTable added" + productTable);
        if (returnedProductTable != null) {
            //productMerchantRepo.updateQuantity(returnedProductTable.getProductStock() + productTable.getProductStock(), productTable.getId());
            LOGGER.debug("Found the ProductTable now updating : {}", productTable);
            productTable=null;
        } else {
            LOGGER.info("Saving the productTable with productId : {} ", productTable.getProductId());
            productRepo.save(productTable);
        }
        return productTable;
    }

    @Override
    public List<Product> searchByName(String productName,Pageable pageable) {
        LOGGER.debug("Searching product with name : {} ", productName);
        List<ProductTable> productTables=productRepo.findByProductNameIgnoreCaseContaining(productName,pageable);
        List<Product> productList=new ArrayList<Product>();
        for (ProductTable productTable:productTables) {
            productList.add(getBestProduct(productTable));
        }
        return productList;
    }

    @Override
    public List<Product> searchByCategory(String category,Pageable pageable) {
        LOGGER.debug("Searching product with name : {} ", category);
        List<ProductTable> productTables= productRepo.findByProductCategory(category,pageable);
        List<Product> productList=new ArrayList<Product>();
        for (ProductTable productTable:productTables) {
            productList.add(getBestProduct(productTable));
        }
        return productList;
    }


    @Override
    @Transactional(readOnly = false)
    public Boolean reduceQuantity(String productId, Integer quant, Integer merchantId){
        ProductMerchant productMerchant = productMerchantRepo.findProductMerchantByProductIdAndAndMerchantId(productId,merchantId);
        if (productMerchant != null) {
            Merchant merchant=productDao.getMerchant(merchantId);
            Integer remainingQuant = productMerchant.getProductStock() - quant;
            Double factor=Utils.configFactor(merchant.getRating(),productMerchant.getProductPrice(),remainingQuant);
            productMerchantRepo.updateQuantity(remainingQuant, factor,productId,merchantId);
            return true;
        } else {
            LOGGER.error("ProductTable with id " + productId + " does not exists");
            return false;
        }
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        Page<ProductTable> productTablePage = productRepo.findAll(pageable);
        return findAllProductsWithPagination(productTablePage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getCategories() {
        List<String> productList = productRepo.findAllDistinct();
        return productList;
    }


    @Override
    public Product getProductByIdAndMerchantId(Integer id, Integer merchantId) {
        ProductTable productTable = productRepo.findById(id);
        LOGGER.debug("ProductTable : {}", productTable);
        ProductMerchant productMerchant = productMerchantRepo.findProductMerchantByProductIdAndAndMerchantId(productTable.getProductId(), merchantId);
        LOGGER.debug("ProductMerchant : {}", productMerchant);
        Merchant merchant = productDao.getMerchant(productMerchant.getMerchantId());
        LOGGER.debug("Merchant : {}", merchant);
        return Utils.setProduct(productTable, productMerchant, merchant);
    }


    @Override
    public Page<Product> findProductByProductId(String productId, Pageable pageable) {
        ProductTable productTable = productRepo.findByProductId(productId);
        return getAllBestProduct(productTable,pageable);
    }

    @Override
    public ProductMerchant getProductMerchantByProductIdAndMerchantId(String productId, Integer merchantId) {
        return productMerchantRepo.findProductMerchantByProductIdAndAndMerchantId(productId, merchantId);
    }

    @Override
    public List<ProductMerchant> findProductMerchantsByProductIdOrderByFactor(String productId) {
        return productMerchantRepo.findProductMerchantsByProductIdOrderByFactor(productId);
    }

    @Override
    public ProductMerchant findProductMerchantByProductIdOrderByFactor(String productId) {
        return productMerchantRepo.findProductMerchantByProductIdOrderByFactor(productId);
    }


    @Override
    public void updateFactor(Float rating, Integer merchantId){
        List<ProductMerchant> productMerchantList=productMerchantRepo.findProductMerchantByMerchantId(merchantId);
        for (ProductMerchant productMerchant:productMerchantList) {
            Double factor=Utils.configFactor(rating,productMerchant.getProductPrice(),productMerchant.getProductStock());
            productMerchantRepo.updateFactor(factor,productMerchant.getProductId(),merchantId);
        }
    }





    private Page<Product> findAllProductsWithPagination(Page<ProductTable> productTablePage) {
        //Page<ProductTable> productTablePage=productRepo.findAll(pageable);
        LOGGER.debug("Total Available pages : {}",productTablePage.getTotalPages());
        final List<Product> productList = new ArrayList<Product>();
        for (ProductTable productTable : productTablePage) {
            productList.add(getBestProduct(productTable));
        }
        PageImpl<Product> productPage=new PageImpl<Product>(productList);
       // productPage.
        return new PageImpl<Product>(productList);
    }

    private List<Product> findAllProductsWithoutPagination(List<ProductTable> productTable) {
        //Page<ProductTable> productTablePage=productRepo.findAll(pageable);
        final List<Product> productList = new ArrayList<Product>();
        for (ProductTable productTableIterator : productTable) {
            productList.add(getBestProduct(productTableIterator));
        }
        return productList;
    }

    private Product getBestProduct(ProductTable productTable) {
        List<ProductMerchant> productMerchants = productMerchantRepo.findProductMerchantsByProductIdOrderByFactor(productTable.getProductId());
        Merchant merchant = productDao.getMerchant(productMerchants.get(0).getMerchantId());
        return Utils.setProduct(productTable, productMerchants.get(0), merchant);
    }

    private Page<Product> getAllBestProduct(ProductTable productTable,Pageable pageable) {
        List<ProductMerchant> productMerchants = productMerchantRepo.findProductMerchantsByProductIdOrderByFactor(productTable.getProductId());
        LOGGER.debug("Product merchants : {} ",productMerchants);
        List<Product> productList=new ArrayList<Product>();
        for (ProductMerchant productMerchant:productMerchants) {
            LOGGER.debug("Product merchant : {} ",productMerchant);
            Merchant merchant = productDao.getMerchant(productMerchant.getMerchantId());
            LOGGER.debug("Merchant info :{} ",merchant);
            productList.add(Utils.setProduct(productTable,productMerchant,merchant));
        }
        //int start = pageable.getOffset();
        //int end = (start + pageable.getPageSize()) > productList.size() ? productList.size() : (start + pageable.getPageSize());
        Page<Product> productPage = new PageImpl<Product>(productList, pageable, productList.size());
        return productPage;
    }
}