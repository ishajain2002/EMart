package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.CartDetails;
import com.example.model.CartMaster;
import com.example.model.Productmaster;
import com.example.model.PurchaseMode;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Integer> {
    Optional<CartDetails> findByCartAndProductAndPurchaseMode(CartMaster cart, Productmaster product, PurchaseMode purchaseMode);
    
    List<CartDetails> findByCart_User_UserId(int userId);
    
    long countByCart_CartId(int cartId);
    List<CartDetails> findByCart(CartMaster cart);
}
