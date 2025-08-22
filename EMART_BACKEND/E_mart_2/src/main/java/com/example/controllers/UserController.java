package com.example.controllers;


import com.example.dtos.UpdateDto;
import com.example.dtos.UserDto;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET current user details (uses JWT)
    @GetMapping("/me")
    public UserDto getLoggedInUser(Principal principal) {
        String username = principal.getName(); // extracted from JWT by Spring Security
        return userService.getUserDetails(username);
    }
    
    @PutMapping("/updateuser")
    public String update(@RequestBody UpdateDto dto, Authentication authentication) {
        String username = authentication.getName(); // Extract logged-in username
        userService.update(username, dto);
        return "Loyalty points updated successfully";
    }
    
//    @PostMapping("/deduct-loyalty")
//    public ResponseEntity<String> deductLoyaltyPoints(Authentication authentication) {
//        String username = authentication.getName();
//        userService.deductLoyaltyPoints(username);
//        return ResponseEntity.ok("Loyalty points deducted successfully.");
//    }
}