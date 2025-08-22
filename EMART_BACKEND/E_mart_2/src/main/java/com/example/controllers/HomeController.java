package com.example.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.ProductdetailsDTO;
import com.example.model.Productmaster;
import com.example.model.ctg_master;
import com.example.services.IHomeService;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class HomeController {

		@Autowired
		IHomeService homeservice;
		
		List<Productmaster> productlist=null;		
		  
		  
	    @GetMapping("/Home")  // <-- Correct mapping: now full path is "/api/Home"
	    public List<ctg_master> getProductCategories()
	    {
	    	return homeservice.getcategories();
	    }
	    
	 
	    @GetMapping("/Home/{s}")
	    public ResponseEntity<?> getSubCategories(@PathVariable String s) {
	        List<ctg_master> subcatlist = homeservice.getsubcategories(s);

	        if (!subcatlist.isEmpty()) 
	        {
	            return ResponseEntity.ok(subcatlist);  
	        }
	        else 
	        {
	           return null;
	        }
	    }
	    

	    @GetMapping("/Home/Products/{s}")
	    public ResponseEntity<?>getProducts(@PathVariable String s)
	    {
	    	 Integer catmasterid = homeservice.getcategorymasterid(s);
	            System.out.println("catmasterid found: " + catmasterid);
	            
	            productlist = homeservice.getproducts(catmasterid);
	            
	            if (!productlist.isEmpty()) 
	            {
	                return ResponseEntity.ok(productlist);  
	            }
	            else 
	            {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No subcategories or products found");
	            }
	    }
	    
	    @GetMapping("/Home/offer")
	    public List<ctg_master>getProductswithoffer()
	    {
	    	return homeservice.getproductswithoffer();
	    }
	    
	    @GetMapping("/Home/getproduct/{product_id}")
	    public Optional<Productmaster> getsingleproduct(@PathVariable Integer product_id)
	    {
	    	return homeservice.getproduct(product_id); 
	    }
	    
	    
	    @GetMapping("/Home/getspecialproduct/{ctg_master_id}")
	    public Optional<Productmaster> getProductsFromProduct(@PathVariable Integer ctg_master_id)
	    {
	    	return homeservice.getproductsfromtable(ctg_master_id);
	    	
	    }
	    
	    @GetMapping("/Home/productdetail/{product_id}")
	    public List<ProductdetailsDTO>getdetails(@PathVariable Integer product_id)
	    {
	    	return homeservice.getproductdetails(product_id);
	    }
}