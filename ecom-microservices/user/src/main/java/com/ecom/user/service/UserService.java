package com.ecom.user.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.user.dto.AddressDTO;
import com.ecom.user.dto.UserRequest;
import com.ecom.user.dto.UserResponse;
import com.ecom.user.entity.Users;
import com.ecom.user.entity.UserAddress;
import com.ecom.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
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
	    Users user = new Users();
	    updateUserFromRequest(user, userRequest);

	    Users savedUser = userRepository.save(user);
	    return mapToUserResponse(savedUser);
	}
	

	public ResponseEntity<Optional<UserResponse>> getUser(String id) {
//	    for (User user : usersList) {
//	        if (user.getId().equals(id)) {
//	            return ResponseEntity.ok(user);
//	        }
//	    }
//	    return ResponseEntity.notFound().build();
		return ResponseEntity.ok(userRepository.findById(String.valueOf(id))
				.map(this::mapToUserResponse));
	}
	
	public ResponseEntity<String> updateUser(String id, UserRequest updatedUserRequest){
//		for(User user: usersList) {
//			if(user.getId().equals(id)) {
//				user.setEmail(updatedUser.getEmail());
//				user.setName(updatedUser.getName());
//				user.setPhone(updatedUser.getPhone());
//				return ResponseEntity.ok("Updated Successfully");
//			}	
//		}
//		return ResponseEntity.notFound().build();
		Optional<Users> existing = userRepository.findById(String.valueOf(id));

	    if(existing.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    Users user = existing.get();
	    user.setName(updatedUserRequest.getName());
	    user.setEmail(updatedUserRequest.getEmail());
	    user.setPhone(updatedUserRequest.getPhone());

	    userRepository.save(user);

	    return ResponseEntity.ok("Updated Successfully");
	}
	
	private void updateUserFromRequest(Users user, UserRequest userRequest) {
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
	
	private UserResponse mapToUserResponse(Users user) {
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
