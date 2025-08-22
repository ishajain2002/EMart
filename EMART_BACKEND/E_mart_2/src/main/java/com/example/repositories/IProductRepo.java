package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.dtos.ProductdetailsDTO;
import com.example.model.Productmaster;


@Repository
public interface IProductRepo extends JpaRepository<Productmaster,Integer>{

	@Query("SELECT p FROM Productmaster p WHERE p.category.ctgMasterId = :ctgmasterid")
	 Optional<Productmaster> findByCtgMasterIdFromProducts(@Param("ctgmasterid") Integer ctgmasterid);

	
	//Finds products where the category.ctdmasterid is same as given
	@Query("SELECT p FROM Productmaster p WHERE p.category.ctgMasterId = :ctgmasterid")
	List<Productmaster> findProductsByCategoryId(@Param("ctgmasterid") Integer ctgmasterid);
	
	
//	@Query("SELECT p FROM Productmaster p where p.productId= :productId")
//	Optional<Productmaster> findProductsByproductId(@Param("productId")Integer productId);
//	
	 @Query(
		        value = "SELECT p.product_id AS productId, c.config_name AS configName, p.config_dtls AS configDtls " +
		                "FROM prod_detail_master p " +
		                "JOIN config_master c ON p.config_id = c.config_id " +
		                "WHERE p.product_id = :productId",
		        nativeQuery = true)
		    List<ProductdetailsDTO> findByProductId(@Param("productId") Integer productId);
	 
	 
	 @Query("SELECT p FROM Productmaster p where p.productId = :productId")
		Optional<Productmaster> findByProductIdfromProductMaster(@Param("productId") Integer productId);
	 
	 
	 
}
