package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.dtos.ProductdetailsDTO;
import com.example.model.Productmaster;
import com.example.model.ctg_master;



//all methods that are being called in controller
public interface IHomeService {
	
	
	//using common interface for categories and products 
	
	Optional<Productmaster> getproductsfromtable(Integer ctg_master_id);
	
    List<ctg_master> getcategories(); // to find categories
    
    List<ctg_master>getsubcategories(String s); //to find subcategories
    
    Integer getcategorymasterid(String s);//to find categorymasterid 
    
    List<Productmaster>getproducts(Integer ctg_master_id);//to fetch product cards
    
    List<ProductdetailsDTO> getproductdetails(Integer product_id);//to fetch product details
    
    List<ctg_master>getproductswithoffer();
    
	Optional<Productmaster> getproduct(Integer product_id); //to fetch products

    
    
}
