package com.eom.order.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eom.order.dto.CartItemRequest;
import com.eom.order.entity.CartItem;
import com.eom.order.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CartController {
	
	
	private final CartService cartService;
	
	@PostMapping("/add-to-cart")
	public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId,
			@RequestBody CartItemRequest request){
			if(!cartService.addToCart(userId, request))
					return ResponseEntity.badRequest()
							.body("Product Out of Stock or User Not Found");
			return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
		
	@DeleteMapping("/delete-from-cart/{productId}")
	public ResponseEntity<Void> deleteFromCart(@RequestHeader("X-User-Id") String userId, @PathVariable String productId){
		boolean deleted = cartService.deleteItemFromCart(userId, productId);
		return deleted ? ResponseEntity.noContent().build() :  ResponseEntity.notFound().build();
		
	}
	
	@GetMapping("/cart")
	public ResponseEntity<List<CartItem>> getCartItems(@RequestHeader("X-User-Id") String userId){
		return ResponseEntity.ok(cartService.getCartItems(userId));
	}
	
	

}
