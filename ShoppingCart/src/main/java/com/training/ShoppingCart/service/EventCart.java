package com.training.ShoppingCart.service;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
public class EventCart {
	private String userID;
	private double amount;
	private String cartId;
}
