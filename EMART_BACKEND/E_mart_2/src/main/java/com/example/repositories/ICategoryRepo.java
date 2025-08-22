package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.ctg_master;



@Repository
public interface ICategoryRepo extends JpaRepository<ctg_master, Integer> {
	
	 // to find categories
    @Query("SELECT c FROM ctg_master c WHERE c.subCtgName IS NULL AND c.flag = false")
    List<ctg_master> findBySubCtgNameIsNullAndFlagFalse();  
    
    
    //to find sub categories using ctg_id="ELE"
    @Query("SELECT c FROM ctg_master c WHERE c.flag = false AND c.subCtgName = :subCtgName")
    List<ctg_master> findBySubCtgNameAndFlag(@Param("subCtgName") String subCtgName);


//    //to find ctgmasterid when once reached a category which has only products inside.
//    @Query("SELECT p.ctgMasterId FROM ctg_master p WHERE p.ctgId = :ctgId")
//   List<Integer> findCtgMasterIdByCtgId(@Param("ctgId") String ctgId);

    
    //to find ctgmasterid when once reached a category which has only products inside.
    @Query("SELECT p FROM ctg_master p WHERE p.ctgId = :ctgId")
   Optional<ctg_master> findCtgMasterIdByCtgId(@Param("ctgId") String ctgId);
   
   
   //to find special products from ctg_master table
   @Query("SELECT c FROM ctg_master c WHERE c.subCtgName IS NULL AND c.flag = true")
   List<ctg_master> findBySubCtgNameIsNullAndFlagTrue();
    
}
