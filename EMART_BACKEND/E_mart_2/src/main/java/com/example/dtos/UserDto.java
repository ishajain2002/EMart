package com.example.dtos;

public class UserDto {
    private String username;
    private String phoneNo;
    private String email;
    private boolean loyalty;
    private int loyaltyPoints;

    // Constructors
    public UserDto() {}

    public UserDto(String username, String phoneNo, String email, boolean loyalty, int loyaltyPoints) {
        this.username = username;
        this.phoneNo = phoneNo;
        this.email = email;
        this.loyalty = loyalty;
        this.loyaltyPoints = loyaltyPoints;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(boolean loyalty) {
		this.loyalty = loyalty;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

    // Getters and setters
    // (You can use Lombok @Data to simplify)
}