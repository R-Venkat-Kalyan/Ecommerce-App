package com.ecom.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.dto.UserRequest;
import com.ecom.user.dto.UserResponse;
import com.ecom.user.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

//	@PostMapping("/add-user")
//	public UserRequest createUser(@RequestBody UserRequest userRequest){
////		System.out.println(user);
//		userService.createUser(userRequest);
//		return userRequest;
//	}

	@PostMapping("/add-user")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		return ResponseEntity.ok(userService.createUser(userRequest));
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> getUsers() {
		return userService.getUsers();
		// return ResponseEntity.ok(userService.getUsers());
		// return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<Optional<UserResponse>> getUser(@PathVariable String id) {
		return userService.getUser(id);
	}

	@PutMapping("/update-user/{id}")
	public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserRequest updatedUserRequest) {
		return userService.updateUser(id, updatedUserRequest);
	}

}
