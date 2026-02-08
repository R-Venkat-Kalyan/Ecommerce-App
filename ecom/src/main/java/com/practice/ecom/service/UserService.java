package com.practice.ecom.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.ecom.dto.AddressDTO;
import com.practice.ecom.dto.UserRequest;
import com.practice.ecom.dto.UserResponse;
import com.practice.ecom.entity.User;
import com.practice.ecom.entity.UserAddress;
import com.practice.ecom.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<List<UserResponse>> getUsers() {
//		if(usersList.size() >= 1)
//			return ResponseEntity.ok(usersList);
//		else
//			return ResponseEntity.notFound().build();
//		return ResponseEntity.ok(userRepository.findAll());
		return ResponseEntity.ok(userRepository.findAll().stream()
				.map(this::mapToUserResponse)
				.collect(Collectors.toList()));
	}

//	public void createUser(UserRequest userRequest) {
//		
//		User user = new User();
//		updateUserFromRequest(user, userRequest);
//		userRepository.save(user);
////		return user;
//	}
	
	public UserResponse createUser(UserRequest userRequest) {
	    User user = new User();
	    updateUserFromRequest(user, userRequest);

	    User savedUser = userRepository.save(user);
	    return mapToUserResponse(savedUser);
	}
	

	public ResponseEntity<Optional<UserResponse>> getUser(Long id) {
//	    for (User user : usersList) {
//	        if (user.getId().equals(id)) {
//	            return ResponseEntity.ok(user);
//	        }
//	    }
//	    return ResponseEntity.notFound().build();
		return ResponseEntity.ok(userRepository.findById(id)
				.map(this::mapToUserResponse));
	}
	
	public ResponseEntity<String> updateUser(Long id, UserRequest updatedUserRequest){
//		for(User user: usersList) {
//			if(user.getId().equals(id)) {
//				user.setEmail(updatedUser.getEmail());
//				user.setName(updatedUser.getName());
//				user.setPhone(updatedUser.getPhone());
//				return ResponseEntity.ok("Updated Successfully");
//			}	
//		}
//		return ResponseEntity.notFound().build();
		Optional<User> existing = userRepository.findById(id);

	    if(existing.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    User user = existing.get();
	    user.setName(updatedUserRequest.getName());
	    user.setEmail(updatedUserRequest.getEmail());
	    user.setPhone(updatedUserRequest.getPhone());

	    userRepository.save(user);

	    return ResponseEntity.ok("Updated Successfully");
	}
	
	private void updateUserFromRequest(User user, UserRequest userRequest) {
		// TODO Auto-generated method stub
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPhone(userRequest.getPhone());
		if(userRequest.getAddress() != null) {
			UserAddress address = new UserAddress();
			address.setStreet(userRequest.getAddress().getStreet());
			address.setZipCode(userRequest.getAddress().getZipCode());
			address.setCity(userRequest.getAddress().getCity());
			address.setState(userRequest.getAddress().getState());
			address.setCountry(userRequest.getAddress().getCountry());
			user.setUserAddress(address);
//			address.setUser(user);
			
		}
		
	}
	
	private UserResponse mapToUserResponse(User user) {
		UserResponse response = new UserResponse();
		response.setId(String.valueOf(user.getId()));
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setPhone(user.getPhone());
		response.setRole(user.getRole());
		
		if(user.getUserAddress() != null) {
			AddressDTO address = new AddressDTO();
			address.setStreet(user.getUserAddress().getStreet());
			address.setZipCode(user.getUserAddress().getZipCode());
			address.setCity(user.getUserAddress().getCity());
			address.setCountry(user.getUserAddress().getCountry());
			address.setState(user.getUserAddress().getState());
			response.setAddress(address);
			
		}
		return response;
	}
	

}
