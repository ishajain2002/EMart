package com.example.repositories;

import com.example.model.InvoiceDetails;
import com.example.model.InvoiceMaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Integer> {
	
	List<InvoiceDetails> findByInvoice(InvoiceMaster invoice);
}
