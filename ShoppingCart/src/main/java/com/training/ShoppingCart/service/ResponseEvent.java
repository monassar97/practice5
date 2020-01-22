package com.training.ShoppingCart.service;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class ResponseEvent {
	private String cartId;
	private String userId;
	private String reply;
}
