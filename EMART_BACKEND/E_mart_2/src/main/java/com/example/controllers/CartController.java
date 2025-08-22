package com.example.controllers;

import com.example.dtos.CartRequestDto;
import com.example.dtos.CartUpdateRequestDto;
import com.example.dtos.CartViewDto;
import com.example.services.CartService;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addProductToCart(@RequestBody CartRequestDto request,Principal principal) {
    	String result = cartService.addProductToCart(request, principal.getName());
        return ResponseEntity.ok(result);
    }
    
    @GetMapping
    public ResponseEntity<List<CartViewDto>> getCartDetails(Principal principal) {
    	 String username = principal.getName();
         
         // You may call a method like: getUserIdByUsername(username)
         int userId = cartService.getUserIdByUsername(username);
        List<CartViewDto> cartDetails = cartService.getCartDetailsByUserId(userId);

        if (cartDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cartDetails);
    }
    
    @PostMapping("/update")
    public ResponseEntity<String> updateQuantities(@RequestBody List<CartUpdateRequestDto> updates) {
        cartService.updateCartQuantities(updates);
        return ResponseEntity.ok("Cart updated successfully.");
    }
    
    @DeleteMapping("/delete/{cartDetailId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable int cartDetailId) {
        cartService.deleteCartItem(cartDetailId);
        return ResponseEntity.ok("Cart item deleted successfully");
    }
}
