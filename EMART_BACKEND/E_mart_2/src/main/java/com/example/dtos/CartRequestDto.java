package com.example.dtos;

import com.example.model.PurchaseMode;

public class CartRequestDto {

	//private int userId;
    private int productId;
//    public int getUserId() {
//		return userId;
//	}
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public PurchaseMode getPurchaseMode() {
		return purchaseMode;
	}
	public void setPurchaseMode(PurchaseMode purchaseMode) {
		this.purchaseMode = purchaseMode;
	}
	private int quantity;
    private PurchaseMode purchaseMode;
}
