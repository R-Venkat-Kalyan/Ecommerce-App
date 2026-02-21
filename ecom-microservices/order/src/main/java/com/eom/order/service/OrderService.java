package com.eom.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eom.order.dto.OrderItemDTO;
import com.eom.order.dto.OrderResponse;
import com.eom.order.entity.CartItem;
import com.eom.order.entity.Order;
import com.eom.order.entity.OrderItem;
import com.eom.order.entity.OrderStatus;
import com.eom.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderService {
	
	
	private final CartService cartService;
	
	private final OrderRepository orderRepository;
	
	public Optional<OrderResponse> createOrder(String userId) {
		
		// Validate the cart items
		List<CartItem> cartItems = cartService.getCartItems(userId);
		if(cartItems.isEmpty()) {
			return Optional.empty();
		}
		
		// Validate for User
//		Optional<User> userList = userRepository.findById(Long.valueOf(userId));
//		if(userList.isEmpty()) {
//			return Optional.empty();
//		}
//		User user = userList.get();
		
		// Calculate total Price
		BigDecimal totalPrice = cartItems.stream()
				.map(CartItem::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		// create order
		Order order = new Order();
		order.setUserId(Long.valueOf(userId));
		order.setStatus(OrderStatus.CONFIRMED);
		order.setTotalAmount(totalPrice);
		List<OrderItem> orderItems = cartItems.stream()
				.map(item -> new OrderItem(
						null, item.getProductId(), item.getQuantity(), item.getPrice(), order
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
							orderItem.getProductId(),
							orderItem.getQuantity(),
							orderItem.getPrice(),
							orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
							))
					.toList(),
					order.getCreatedAt()
				);
	}
	

}
