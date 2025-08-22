package com.example.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.dtos.InvoiceDetailsDto;
import com.example.dtos.InvoiceRequestDto;
import com.example.dtos.InvoiceViewDto;
import com.example.services.InvoiceService;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generate")
    public String generateInvoice(@RequestBody InvoiceRequestDto dto, Authentication authentication) {
        return invoiceService.generateInvoice(dto, authentication);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<InvoiceViewDto>> getAllInvoicesForUser(Authentication auth) {
        String username = auth.getName();
        List<InvoiceViewDto> invoiceList = invoiceService.getAllInvoicesForUser(username);
        return ResponseEntity.ok(invoiceList);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<List<InvoiceDetailsDto>> getInvoiceDetails(
            @PathVariable int invoiceId, Authentication auth) {
        String username = auth.getName();
        List<InvoiceDetailsDto> details = invoiceService.getInvoiceDetails(invoiceId, username);
        return ResponseEntity.ok(details);
    }
}