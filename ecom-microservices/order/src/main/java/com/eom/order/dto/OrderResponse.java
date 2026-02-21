package com.eom.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.eom.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

	private Long id;
	private BigDecimal totalAmount;
	private OrderStatus status;
	private List<OrderItemDTO> items;
	private LocalDateTime createdAt;

}
