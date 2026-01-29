package com.practice.ecom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.ecom.dto.UserRequest;
import com.practice.ecom.dto.UserResponse;
import com.practice.ecom.entity.User;
import com.practice.ecom.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add-user")
	public UserRequest createUser(@RequestBody UserRequest userRequest){
//		System.out.println(user);
		userService.createUser(userRequest);
		return userRequest;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> getUsers(){
		return userService.getUsers();
		//return ResponseEntity.ok(userService.getUsers());
		//return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Optional<UserResponse>> getUser(@PathVariable Long id) {
	    return userService.getUser(id);
	}
	
	@PutMapping("/update-user/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest updatedUserRequest){
		return userService.updateUser(id, updatedUserRequest);
	}
	

}
