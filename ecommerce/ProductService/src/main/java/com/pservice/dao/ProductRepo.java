package com.pservice.dao;

//import com.pservice.model.ProductTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.coviam.model.*;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by Govinda on 25/07/17.
 */

@Repository
public interface ProductRepo extends PagingAndSortingRepository<ProductTable, Integer> {


    public List<ProductTable> findByProductNameIgnoreCaseContaining(String pname,Pageable pageable);


    @Query("SELECT DISTINCT(p.productCategory) FROM ProductTable p")
    public List<String> findAllDistinct();

     public ProductTable findByProductId(String productId);

    public ProductTable findById(Integer id);

    public List<ProductTable> findByProductCategory(String category,Pageable pageable);


    //public Page<ProductTable> findAll(Pageable pageable);
}
