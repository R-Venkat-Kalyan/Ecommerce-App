package com.eom.order.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
	
	private Long id;
	private String productId;
	private Integer quantity;
	private BigDecimal price;
	private BigDecimal subTotal;

}
