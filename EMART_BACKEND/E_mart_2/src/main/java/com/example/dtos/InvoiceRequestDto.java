package com.example.dtos;

public class InvoiceRequestDto {
    private double totalPayment;
    private double tax;
    private double finalPayment;

    // Getters and Setters
    public double getTotalPayment() { return totalPayment; }
    public void setTotalPayment(double totalPayment) { this.totalPayment = totalPayment; }

    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }

    public double getFinalPayment() { return finalPayment; }
    public void setFinalPayment(double finalPayment) { this.finalPayment = finalPayment; }
}
