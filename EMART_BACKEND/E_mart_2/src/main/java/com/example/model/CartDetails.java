package com.example.model;


import jakarta.persistence.*;

@Entity
@Table(name = "cart_details")
public class CartDetails {

	    public int getCartDetailId() {
		return cartDetailId;
	}

	public void setCartDetailId(int cartDetailId) {
		this.cartDetailId = cartDetailId;
	}

	public CartMaster getCart() {
		return cart;
	}

	public void setCart(CartMaster cart) {
		this.cart = cart;
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

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "cart_d_id")
	    private int cartDetailId;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "cart_id", nullable = false)
	    private CartMaster cart;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "product_id", nullable = false)
	    private Productmaster product;

	    @Column(name = "quantity", nullable = false)
	    private int quantity;

	    @Column(name = "mrp", nullable = false)
	    private double mrp;

	    @Column(name = "loyal_price", nullable = false)
	    private double loyalPrice;

	    @Column(name = "loyalty_points", nullable = false)
	    private int loyaltyPoints;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "purchase_mode", nullable = false)
	    private PurchaseMode purchaseMode;
}
