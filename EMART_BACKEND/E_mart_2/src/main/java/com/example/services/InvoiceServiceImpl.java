package com.example.services;


import com.example.dtos.InvoiceDetailsDto;
import com.example.dtos.InvoiceRequestDto;
import com.example.dtos.InvoiceViewDto;
import com.example.model.*;
import com.example.repositories.CartDetailsRepository;
import com.example.repositories.CartMasterRepository;
import com.example.repositories.InvoiceDetailsRepository;
import com.example.repositories.InvoiceMasterRepository;
import com.example.repositories.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired private UserRepository userRepository;
    @Autowired private CartMasterRepository cartMasterRepo;
    @Autowired private CartDetailsRepository cartDetailsRepo;
    @Autowired private InvoiceMasterRepository invoiceMasterRepo;
    @Autowired private InvoiceDetailsRepository invoiceDetailsRepo;

    @Transactional
    public String generateInvoice(InvoiceRequestDto dto, Authentication authentication) {
        String username = authentication.getName(); // From JWT
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        User user = optionalUser.get();

        CartMaster cartMaster = cartMasterRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("No active cart found"));

        List<CartDetails> cartDetailsList = cartDetailsRepo.findByCart(cartMaster);

        // Create invoice master
        InvoiceMaster invoiceMaster = new InvoiceMaster();
        invoiceMaster.setUser(user);
        invoiceMaster.setInvoiceDate(LocalDate.now());
        invoiceMaster.setTotalPayment(dto.getTotalPayment());
        invoiceMaster.setTax(dto.getTax());
        invoiceMaster.setFinalPayment(dto.getFinalPayment());

        invoiceMasterRepo.save(invoiceMaster);

        // Create invoice details
        for (CartDetails cd : cartDetailsList) {
            InvoiceDetails id = new InvoiceDetails();
            id.setInvoice(invoiceMaster);
            id.setProduct(cd.getProduct());
            id.setQuantity(cd.getQuantity());
            id.setMrp(cd.getMrp());
            id.setLoyalPrice(cd.getLoyalPrice());
            id.setLoyaltyPoints(cd.getLoyaltyPoints());
            id.setPurchaseMode(cd.getPurchaseMode());

            invoiceDetailsRepo.save(id);
        }

        // Clean up cart
        cartDetailsRepo.deleteAll(cartDetailsList);
        cartMasterRepo.delete(cartMaster);

        return "Invoice created successfully!";
    }
    public List<InvoiceViewDto> getAllInvoicesForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<InvoiceMaster> invoices = invoiceMasterRepo.findByUser(user);

        return invoices.stream().map(invoice -> {
            InvoiceViewDto dto = new InvoiceViewDto();
            dto.setInvoiceId(invoice.getInvoiceId());
            dto.setInvoiceDate(invoice.getInvoiceDate().toString());
            dto.setTotalPayment(invoice.getTotalPayment());
            dto.setTax(invoice.getTax());
            dto.setFinalPayment(invoice.getFinalPayment());
            return dto;
        }).collect(Collectors.toList());
    }
    
    public List<InvoiceDetailsDto> getInvoiceDetails(int invoiceId, String username) {
        InvoiceMaster invoice = invoiceMasterRepo.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        if (!invoice.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Access denied to invoice");
        }

        List<InvoiceDetails> details = invoiceDetailsRepo.findByInvoice(invoice);

        return details.stream().map(d -> {
            Productmaster product = d.getProduct(); // already loaded due to relation

            InvoiceDetailsDto dto = new InvoiceDetailsDto();
            dto.setProductId(product.getProductId());
            dto.setProductName(product.getProdName());
            dto.setProductImg(product.getProductImg());
            dto.setQuantity(d.getQuantity());
            dto.setPurchaseMode(d.getPurchaseMode().toString());

            // Apply the logic based on purchase mode
            switch (d.getPurchaseMode()) {
                case MRP:
                    dto.setUnitPrice(product.getMrpPrice());
                    dto.setSubtotal(product.getMrpPrice() * d.getQuantity());
                    break;
                case LOYAL_PRICE:
                    dto.setUnitPrice(product.getLoyalPrice());
                    dto.setSubtotal(product.getLoyalPrice() * d.getQuantity());
                    break;
                case LOYALTY_POINTS:
                    dto.setUnitPrice(product.getLoyalPrice());
                    dto.setSubtotal(product.getLoyalPrice()*d.getQuantity());
                    break;
            }

            return dto;
        }).collect(Collectors.toList());
    }
}