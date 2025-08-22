package com.example.dtos;

public class CartViewDto {

	    public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPurchaseMode() {
		return purchaseMode;
	}
	public void setPurchaseMode(String purchaseMode) {
		this.purchaseMode = purchaseMode;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	    private int cartDetailId;
		public int getCartDetailId() {
			return cartDetailId;
		}
		public void setCartDetailId(int cartDetailId) {
			this.cartDetailId = cartDetailId;
		}
		public int getLoyaltyPoints() {
			return loyaltyPoints;
		}
		public void setLoyaltyPoints(int loyaltyPoints) {
			this.loyaltyPoints = loyaltyPoints;
		}
		private int productId;
	    private String productName;
	    private String productImg;
	    private int quantity;
	    private String purchaseMode; // MRP, LOYALTY_POINTS, LOYAL_PRICE
	    private int unitPrice;
	    private int subtotal;
	    private int loyaltyPoints;
}
