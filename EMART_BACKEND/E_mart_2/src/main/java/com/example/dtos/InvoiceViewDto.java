package com.example.dtos;

public class InvoiceViewDto {
    private int invoiceId;
    private String invoiceDate;
    private double totalPayment;
    private double tax;
    private double finalPayment;
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public double getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getFinalPayment() {
		return finalPayment;
	}
	public void setFinalPayment(double finalPayment) {
		this.finalPayment = finalPayment;
	}

    // Getters and Setters
}
