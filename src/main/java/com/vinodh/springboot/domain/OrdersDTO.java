package com.vinodh.springboot.domain;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinodh.springboot.entity.Orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdersDTO {

	@JsonProperty(value = "orderId")
	private long orderId;
	@JsonProperty(value = "totPrice")
	private double totPrice;
	@JsonProperty(value = "orderDesc")
	private String orderDesc;
	@JsonProperty(value = "orderDt")
	private Date orderDt;

	/* Copy Constructor to fetch Convert Entity to DTO */
	public OrdersDTO(Orders orders) {
		if (Objects.nonNull(orders)) {
			this.orderId = orders.getOrderId();
			this.totPrice = orders.getTotPrice();
			this.orderDesc = orders.getOrderDesc();
			this.orderDt = orders.getOrderDt();
		}
	}
}
