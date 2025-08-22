package com.example.repositories;
import com.example.model.InvoiceMaster;
import com.example.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceMasterRepository extends JpaRepository<InvoiceMaster, Integer> {
	
	List<InvoiceMaster> findByUser(User user);
}
