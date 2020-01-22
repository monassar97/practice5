package com.training.ShoppingCart.service;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class EventCart {
	private String userId;
	private double amount;
	private String cartId;
}
