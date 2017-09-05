package com.pservice.dao;

//import com.pservice.model.ProductMerchant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.coviam.model.*;


import java.util.List;

/**
 * Created by coviam on 12/08/17.
 */
public interface ProductMerchantRepo extends CrudRepository<ProductMerchant,Integer> {

    public ProductMerchant findProductMerchantByProductIdAndAndMerchantId(String productId,Integer merchantId);

    public List<ProductMerchant> findProductMerchantsByProductIdOrderByFactor(String productId);

    public ProductMerchant findProductMerchantByProductIdOrderByFactor(String productId);

    public List<ProductMerchant> findProductMerchantByMerchantId(Integer merchantId);

    @Modifying
    @Query("UPDATE ProductMerchant p SET p.productStock=:pstock ,p.factor=:factor WHERE p.productId= :productId AND p.merchantId=:merchantId")
    public void updateQuantity(@Param("pstock") Integer pstock,@Param("factor") Double factor, @Param("productId") String productId, @Param("merchantId") Integer merchantId);

    @Modifying
    @Query("UPDATE ProductMerchant p SET p.factor=:factor WHERE p.productId= :productId AND p.merchantId=:merchantId")
    public void updateFactor(@Param("factor") Double factor, @Param("productId") String productId, @Param("merchantId") Integer merchantId);

    //public Integer countByProduct
}
