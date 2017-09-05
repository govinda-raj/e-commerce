package com.merchant.dao;

import com.coviam.model.Merchant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by coviam on 09/08/17.
 */
@Repository
public interface MerchantDao extends CrudRepository<Merchant, Integer> {

    public Merchant getMerchantByMerchantId(Integer merchantId);

    @Modifying
    @Query("update Merchant m set m.rating=:rating where m.merchantId=:merchantId")
    public void updateRating(@Param("rating") Float rating,@Param("merchantId") Integer merchantId);


    public Merchant getMerchantByEmailId(String merchantEmail);


}
