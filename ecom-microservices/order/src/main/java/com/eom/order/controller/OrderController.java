package com.eom.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eom.order.dto.OrderResponse;
import com.eom.order.service.OrderService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/place-order")
	public ResponseEntity<OrderResponse> createOrder(@RequestHeader("X-User-ID") String userId) {

		return orderService.createOrder(userId)
				.map(orderResponse -> ResponseEntity.status(HttpStatus.CREATED).body(orderResponse))
				.orElseGet(() -> ResponseEntity.badRequest().build());
	}

}
