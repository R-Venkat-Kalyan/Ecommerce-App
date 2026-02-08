package com.practice.ecom.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.ecom.dto.CartItemRequest;
import com.practice.ecom.entity.CartItem;
import com.practice.ecom.entity.Product;
import com.practice.ecom.entity.User;
import com.practice.ecom.repository.CartItemRepository;
import com.practice.ecom.repository.ProductRepository;
import com.practice.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	public boolean addToCart(String userId, CartItemRequest request) {

		Optional<Product> productOpt = productRepository.findById(request.getProductId());

		if (productOpt.isEmpty())
			return false;

		Product product = productOpt.get();
		if (product.getStockQuantity() < request.getQuantity())
			return false;
		Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

		if (userOpt.isEmpty())
			return false;

		User user = userOpt.get();

		CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
		if (existingCartItem != null) {
			// Product already exists, update quantity
			existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
			existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
			cartItemRepository.save(existingCartItem);
		} else {
			// Product doesn't exist, Create new cart
			CartItem cartItem = new CartItem();
			cartItem.setUser(user);
			cartItem.setProduct(product);
			cartItem.setQuantity(request.getQuantity());
			cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
			cartItemRepository.save(cartItem);
		}

		return true;

	}

	public boolean deleteItemFromCart(String userId, Long productId) {
		Optional<Product> productOpt = productRepository.findById(productId);
		Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

		if (productOpt.isPresent() && userOpt.isPresent()) {
		
			cartItemRepository.deleteByUserAndProduct(userOpt.get(), productOpt.get());
			return true;
		}
		return false;
	}

	public List<CartItem> getCartItems(String userId) {
//		Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
//
//		if (userOpt.isPresent()) {
//			return cartItemRepository.findCartItemsByUser(userId);
//		}
//		return null;
		return userRepository.findById(Long.valueOf(userId))
				.map(cartItemRepository::findByUser)
				.orElseGet(List::of);
	}

	public void clearCart(String userId) {
		userRepository.findById(Long.valueOf(userId))
		.ifPresent(cartItemRepository::deleteByUser);
		
	}

}
