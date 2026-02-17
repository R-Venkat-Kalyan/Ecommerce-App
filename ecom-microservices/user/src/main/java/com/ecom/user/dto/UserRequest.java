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
	
	
//	public UserRequest(String id, String name, String email, Long phone, UserRole role, AddressDTO address) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.email = email;
//		this.phone = phone;
//		this.role = role;
//		this.address = address;
//	}
//	
//	
//	
//	public UserRequest() {
//		super();
//	}
//
//
//
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public Long getPhone() {
//		return phone;
//	}
//	public void setPhone(Long phone) {
//		this.phone = phone;
//	}
//	public UserRole getRole() {
//		return role;
//	}
//	public void setRole(UserRole role) {
//		this.role = role;
//	}
//	public AddressDTO getAddress() {
//		return address;
//	}
//	public void setAddress(AddressDTO address) {
//		this.address = address;
//	}
//	

}
