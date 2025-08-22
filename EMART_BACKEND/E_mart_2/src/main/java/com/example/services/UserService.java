package com.example.services;

import com.example.dtos.RegisterRequest;
import com.example.dtos.UpdateDto;
import com.example.dtos.UserDto;

public interface UserService {

	UserDto getUserDetails(String username);
	public void register(RegisterRequest request);
	void update(String username, UpdateDto dto);
//	public void deductLoyaltyPoints(String username);
}