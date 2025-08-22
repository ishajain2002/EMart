package com.example.services;

import com.example.dtos.CartRequestDto;
import com.example.dtos.CartUpdateRequestDto;
import com.example.dtos.CartViewDto;
import com.example.model.PurchaseMode;
import com.example.model.*;
import com.example.repositories.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IProductRepo productRepository;

    @Autowired
    private CartMasterRepository cartRepository;

    @Autowired
    private CartDetailsRepository cartDetailRepository;

    @Override
    public String addProductToCart(CartRequestDto request, String username) {
    	 User user = userRepository.findByUsername(username)
    		        .orElseThrow(() -> new RuntimeException("User not found"));

        Productmaster product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartMaster cart = cartRepository.findAll().stream()
                .filter(c -> c.getUser().getUserId() == user.getUserId())
                .findFirst()
                .orElse(null);

        if (cart == null) {
            cart = new CartMaster();
            cart.setCartDate(LocalDate.now());
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        CartDetails cartDetail = cartDetailRepository
                .findByCartAndProductAndPurchaseMode(cart, product, request.getPurchaseMode())
                .orElse(null);

        if (cartDetail == null) {
            cartDetail = new CartDetails();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setPurchaseMode(request.getPurchaseMode());
            cartDetail.setQuantity(request.getQuantity());

            cartDetail.setMrp(product.getMrpPrice());
            cartDetail.setLoyalPrice(product.getLoyalPrice());
            cartDetail.setLoyaltyPoints(product.getLoyaltyPoints());
        } else {
//            cartDetail.setQuantity(cartDetail.getQuantity() + request.getQuantity());
            cartDetail.setQuantity(request.getQuantity());

        }

        cartDetailRepository.save(cartDetail);
        return "Product added to cart successfully";
    }
    
    public List<CartViewDto> getCartDetailsByUserId(int userId) {
        List<CartDetails> cartDetailsList = cartDetailRepository.findByCart_User_UserId(userId);

        List<CartViewDto> dtoList = new ArrayList<>();

        for (CartDetails cd : cartDetailsList) {
            Productmaster product = cd.getProduct();

            CartViewDto dto = new CartViewDto();
            dto.setCartDetailId(cd.getCartDetailId());
            dto.setProductId(product.getProductId());
            dto.setProductName(product.getProdName());
            dto.setProductImg(product.getProductImg());
            dto.setQuantity(cd.getQuantity());
            dto.setPurchaseMode(cd.getPurchaseMode().toString());
          

            // Set unitPrice based on purchaseMode
            switch (cd.getPurchaseMode()) {
                case MRP:
                    dto.setUnitPrice(product.getMrpPrice());
                    dto.setSubtotal(product.getMrpPrice() * cd.getQuantity());
                    dto.setLoyaltyPoints(0);
                    break;
                case LOYAL_PRICE:
                    dto.setUnitPrice(product.getLoyalPrice());
                    dto.setSubtotal(product.getLoyalPrice() * cd.getQuantity());
                    dto.setLoyaltyPoints(0);

                    
                    break;
                case LOYALTY_POINTS:
//                    dto.setUnitPrice(product.getMrpPrice()-product.getLoyaltyPoints());
                	 dto.setUnitPrice(product.getLoyalPrice());
//                    dto.setSubtotal((product.getMrpPrice()*cd.getQuantity())-(product.getLoyaltyPoints() * cd.getQuantity()));
                    dto.setSubtotal((product.getLoyalPrice()*cd.getQuantity()));

                    dto.setLoyaltyPoints(product.getLoyaltyPoints());
                    
                    

                    break;
            }

            dtoList.add(dto);
        }

        return dtoList;
    }
    
    public void updateCartQuantities(List<CartUpdateRequestDto> updates) {
        for (CartUpdateRequestDto dto : updates) {
            CartDetails cartDetail = cartDetailRepository.findById(dto.getCartDetailId())
                    .orElseThrow(() -> new RuntimeException("Cart detail not found for ID: " + dto.getCartDetailId()));

            cartDetail.setQuantity(dto.getQuantity());

            cartDetailRepository.save(cartDetail);
        }
    }
    
    public void deleteCartItem(int cartDetailId) {
        CartDetails cartDetail = cartDetailRepository.findById(cartDetailId)
            .orElseThrow(() -> new RuntimeException("Cart detail not found"));

        int cartId = cartDetail.getCart().getCartId();

        // Delete the item
        cartDetailRepository.deleteById(cartDetailId);

        // Check if any other items exist in the same cart
        long remaining = cartDetailRepository.countByCart_CartId(cartId);
        if (remaining == 0) {
        	cartRepository.deleteById(cartId);
        }
    }
    
    public int getUserIdByUsername(String username) {
        // Use UserRepository to fetch user ID from username
    	Optional<User> optionalUser =  userRepository.findByUsername(username);
    	if (!optionalUser.isPresent()) {
    	    throw new UsernameNotFoundException("User not found with email: " + username);
    	}
    	User user = optionalUser.get();
        
        return user.getUserId(); // assuming this method exists
    }
}