package com.ecom.user.dto;

import com.ecom.user.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	private String id;
	private String name;
	private String email;
	private Long phone;
	private UserRole role;
	private AddressDTO address;
	
}
