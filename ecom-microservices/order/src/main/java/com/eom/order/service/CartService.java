package com.eom.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eom.order.dto.CartItemRequest;
import com.eom.order.entity.CartItem;
import com.eom.order.repository.CartItemRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {


	private final CartItemRepository cartItemRepository;

	public boolean addToCart(String userId, CartItemRequest request) {
//
//		Optional<Product> productOpt = productRepository.findById(request.getProductId());
//
//		if (productOpt.isEmpty())
//			return false;
//
//		Product product = productOpt.get();
//		if (product.getStockQuantity() < request.getQuantity())
//			return false;
//		Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
//
//		if (userOpt.isEmpty())
//			return false;
//
//		User user = userOpt.get();
//
		CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, request.getProductId());
		if (existingCartItem != null) {
			// Product already exists, update quantity
			existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
			existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
			cartItemRepository.save(existingCartItem);
		} else {
			// Product doesn't exist, Create new cart
			CartItem cartItem = new CartItem();
			cartItem.setUserId(userId);
			cartItem.setProductId(request.getProductId());
			cartItem.setQuantity(request.getQuantity());
			cartItem.setPrice(BigDecimal.valueOf(1000.00));
			cartItemRepository.save(cartItem);
		}

		return true;

	}

	public boolean deleteItemFromCart(String userId, String productId) {
		CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

		if (cartItem != null) {
		
			cartItemRepository.delete(cartItem);
			return true;
		}
		return false;
	}

	public List<CartItem> getCartItems(String userId) {
		return cartItemRepository.findByUserId(userId);
	}

	public void clearCart(String userId) {
		cartItemRepository.deleteByUserId(userId);
		
	}

}
