package com.practice.ecom.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.ecom.dto.OrderItemDTO;
import com.practice.ecom.dto.OrderResponse;
import com.practice.ecom.entity.CartItem;
import com.practice.ecom.entity.Order;
import com.practice.ecom.entity.OrderItem;
import com.practice.ecom.entity.OrderStatus;
import com.practice.ecom.entity.User;
import com.practice.ecom.repository.OrderRepository;
import com.practice.ecom.repository.UserRepository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class OrdeService {
	
	@Autowired
	private CartService cartService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Optional<OrderResponse> createOrder(String userId) {
		
		// Validate the cart items
		List<CartItem> cartItems = cartService.getCartItems(userId);
		if(cartItems.isEmpty()) {
			return Optional.empty();
		}
		
		// Validate for User
		Optional<User> userList = userRepository.findById(Long.valueOf(userId));
		if(userList.isEmpty()) {
			return Optional.empty();
		}
		User user = userList.get();
		
		// Calculate total Price
		BigDecimal totalPrice = cartItems.stream()
				.map(CartItem::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		// create order
		Order order = new Order();
		order.setUser(user);
		order.setStatus(OrderStatus.CONFIRMED);
		order.setTotalAmount(totalPrice);
		List<OrderItem> orderItems = cartItems.stream()
				.map(item -> new OrderItem(
						null, item.getProduct(), item.getQuantity(), item.getPrice(), order
						)).toList();
		order.setItems(orderItems);
		Order savedOrder = orderRepository.save(order);
		
		// Clear the cart
		cartService.clearCart(userId);
		return Optional.of(mapToOrderResponse(savedOrder));
	}

	private OrderResponse mapToOrderResponse(Order order) {
		return new OrderResponse(
				order.getId(),
				order.getTotalAmount(),
				order.getStatus(),
				order.getItems().stream()
					.map(orderItem -> new OrderItemDTO(
							orderItem.getId(),
							orderItem.getProduct().getId(),
							orderItem.getQuantity(),
							orderItem.getPrice(),
							orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
							))
					.toList(),
					order.getCreatedAt()
				);
	}
	

}
