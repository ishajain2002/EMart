package com.example.dtos;

public class CartUpdateRequestDto {

	private int cartDetailId;
    private int quantity;
	public int getCartDetailId() {
		return cartDetailId;
	}
	public void setCartDetailId(int cartDetailId) {
		this.cartDetailId = cartDetailId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

