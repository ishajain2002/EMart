package com.example.services;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.example.dtos.InvoiceDetailsDto;
import com.example.dtos.InvoiceRequestDto;
import com.example.dtos.InvoiceViewDto;

public interface InvoiceService {
 
	public String generateInvoice(InvoiceRequestDto dto, Authentication authentication);
	public List<InvoiceDetailsDto> getInvoiceDetails(int invoiceId, String username);
	public List<InvoiceViewDto> getAllInvoicesForUser(String username);
}
