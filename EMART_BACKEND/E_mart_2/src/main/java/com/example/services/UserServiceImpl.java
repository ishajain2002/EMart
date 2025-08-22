package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dtos.RegisterRequest;
import com.example.dtos.UpdateDto;
import com.example.dtos.UserDto;
import com.example.model.CartDetails;
import com.example.model.CartMaster;
import com.example.model.PurchaseMode;
import com.example.model.User;
import com.example.repositories.CartDetailsRepository;
import com.example.repositories.CartMasterRepository;
import com.example.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CartMasterRepository cartMasterRepository;

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    public void register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhoneNo(request.getPhoneNo());
        user.setLoyalty(request.isLoyalty());
        user.setLoyaltyPoints(0);
        userRepo.save(user);
    }
    
    @Override
    public UserDto getUserDetails(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserDto(
                user.getUsername(),
                user.getPhoneNo(),
                user.getEmail(),
                user.isLoyalty(),
                user.getLoyaltyPoints()
        );
    }
    
    @Override
    public void update(String username,UpdateDto dto) {
        Optional<User> optionalUser = userRepo.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println(dto.getLoyaltyPoints());
            user.setLoyaltyPoints(dto.getLoyaltyPoints());
            userRepo.save(user);
        } else {
            throw new RuntimeException("User not found: " + username);
        }
    }
    
//    public void deductLoyaltyPoints(String username) {
//        User user = userRepo.findByUsername(username).orElseThrow();
//
//        // Get user's current cart (you probably have only one open cart)
//        CartMaster cart = cartMasterRepository.findByUser(user).orElseThrow();
//
//        // Get only products purchased via LOYALTY_POINTS
//        List<CartDetails> loyaltyItems = cartDetailsRepository.findByCartAndPurchaseMode(cart, PurchaseMode.LOYALTY_POINTS);
//
//        // Total loyalty points to deduct
//        
//        int totalPointsToDeduct=0;
//            totalPointsToDeduct = loyaltyItems.stream()
//                .mapToInt(item -> item.getProduct().getLoyaltyPoints() * item.getQuantity())
//                .sum();
//
//        // Deduct points
//        int updatedPoints = user.getLoyaltyPoints() - totalPointsToDeduct;
//        user.setLoyaltyPoints(Math.max(updatedPoints, 0)); // Ensure non-negative
//
//        userRepo.save(user);
//    }
}