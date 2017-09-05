package com.cservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//import com.cservice.model.Cart;
import com.coviam.model.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart,Integer>{
	
	public int deleteByUserEmailAndProductId(String email, Integer productId);

	public List<Cart> findByUserEmail(String userMail);
	public void deleteByUserEmail(String userEmail);

	 @Modifying
	 @Query("UPDATE Cart cart SET cart.productQuantity=:productQuantity WHERE cart.id= :id")
	 public void updateProductQuantityById(@Param("productQuantity") Integer productQuantity,@Param("id") Integer id);
	 public void removeById(Integer cartId);
}
