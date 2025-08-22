package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.CartMaster;
import com.example.model.User;

public interface CartMasterRepository extends JpaRepository<CartMaster, Integer> {
    Optional<CartMaster> findByUser(User user);
}
