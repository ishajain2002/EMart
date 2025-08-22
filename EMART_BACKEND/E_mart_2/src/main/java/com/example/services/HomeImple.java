package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dtos.ProductdetailsDTO;
import com.example.model.Productmaster;
import com.example.model.ctg_master;
import com.example.repositories.ICategoryRepo;
import com.example.repositories.IProductRepo;



@Service
public class HomeImple implements IHomeService{

	//USED TO CALL THE METHODS WHICH GET DATA FROM THE CATEGORY TABLE
    @Autowired
    private ICategoryRepo CategoryRepo;
    
    //USED TO CALL THE METHODS WHICH GET DATA FROM THE PRODUCT TABLE
    @Autowired
    private IProductRepo productrepo;

    
    
    //METHOD IS CALLING A QUERY WHICH FETCHES LANDING CATEGORIES FROM THE TABLE
     @Override
    public List<ctg_master> getcategories() {
        return CategoryRepo.findBySubCtgNameIsNullAndFlagFalse();
    }
    
    

    //METHOD IS CALLING A QUERY WHICH FETCHES THE SUBCATEGORIES FROM THE SAME TABLE AS ABOVE
	@Override
	public List<ctg_master> getsubcategories(String s) {
		// TODO Auto-generated method stub
		return CategoryRepo.findBySubCtgNameAndFlag(s);
	}
	
	
	@Override
	public Integer getcategorymasterid(String s)
	{
		Optional<ctg_master> ids = CategoryRepo.findCtgMasterIdByCtgId(s);
		ctg_master obj=ids.get();
		Integer I= obj.getCtgMasterId();
		return I;
	}

	
	//METHOD IS CALLING A QUERY WHICH WILL FETCH THE PRODUCTS BASED ON ctg_master_id
	@Override
	public List<Productmaster>getproducts(Integer ctg_master_id)
	{
		
		return productrepo.findProductsByCategoryId(ctg_master_id);
	}
	
	
	@Override
	public 	List<ProductdetailsDTO> getproductdetails(Integer product_id)
	{
		List<ProductdetailsDTO>f=productrepo.findByProductId(product_id);
		return f;
	}
	
	@Override
	public List<ctg_master>getproductswithoffer()
	{
		return CategoryRepo.findBySubCtgNameIsNullAndFlagTrue();
	}
	
	@Override
	public Optional<Productmaster> getproductsfromtable(Integer ctg_master_id)
	{
		return productrepo.findByCtgMasterIdFromProducts(ctg_master_id);
	}
	
	@Override 
	public Optional<Productmaster>getproduct(Integer product_id)
	{
		return productrepo.findByProductIdfromProductMaster(product_id);	
	}
	
	
}
