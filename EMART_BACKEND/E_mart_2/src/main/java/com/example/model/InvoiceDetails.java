package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetails {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "invoice_dtl_id")
	    private int invoiceDtlId;

	    @ManyToOne
	    @JoinColumn(name = "invoice_id")
	    private InvoiceMaster invoice;

	    public int getInvoiceDtlId() {
			return invoiceDtlId;
		}

		public void setInvoiceDtlId(int invoiceDtlId) {
			this.invoiceDtlId = invoiceDtlId;
		}

		public InvoiceMaster getInvoice() {
			return invoice;
		}

		public void setInvoice(InvoiceMaster invoice) {
			this.invoice = invoice;
		}

		public Productmaster getProduct() {
			return product;
		}

		public void setProduct(Productmaster product) {
			this.product = product;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getMrp() {
			return mrp;
		}

		public void setMrp(double mrp) {
			this.mrp = mrp;
		}

		public double getLoyalPrice() {
			return loyalPrice;
		}

		public void setLoyalPrice(double loyalPrice) {
			this.loyalPrice = loyalPrice;
		}

		public int getLoyaltyPoints() {
			return loyaltyPoints;
		}

		public void setLoyaltyPoints(int loyaltyPoints) {
			this.loyaltyPoints = loyaltyPoints;
		}

		public PurchaseMode getPurchaseMode() {
			return purchaseMode;
		}

		public void setPurchaseMode(PurchaseMode purchaseMode) {
			this.purchaseMode = purchaseMode;
		}

		@ManyToOne
	    @JoinColumn(name = "product_id")
	    private Productmaster product;

	    @Column(name = "quantity")
	    private int quantity;

	    @Column(name = "mrp")
	    private double mrp;

	    @Column(name = "loyal_price")
	    private double loyalPrice;

	    @Column(name = "loyalty_points")
	    private int loyaltyPoints;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "purchase_mode")
	    private PurchaseMode purchaseMode;
}
