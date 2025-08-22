package com.example.model;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public boolean isLoyalty() {
		return loyalty;
	}

	public void setLoyalty(boolean loyalty) {
		this.loyalty = loyalty;
	}

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private int userId;

	    @Column(name = "username", length = 30, unique = true)
	    private String username;

	    @Column(name = "phone_no", length = 10)
	    private String phoneNo;

	    @Column(name = "email", length = 320)
	    private String email;

	    @Column(name = "password", length = 100)
	    private String password;

	    @Column(name = "loyalty_points")
	    private int loyaltyPoints;

	    @Column(name = "loyalty")
	    private boolean loyalty;
}
