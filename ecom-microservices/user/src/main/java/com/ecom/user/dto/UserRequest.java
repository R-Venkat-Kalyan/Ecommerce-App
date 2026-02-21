package com.ecom.user.dto;

import com.ecom.user.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	private String id;
	private String name;
	private String email; 
	private Long phone;
	private UserRole role;
	@JsonProperty("userAddress")
	private AddressDTO address;

}
