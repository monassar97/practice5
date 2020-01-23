package com.training.payment.adapter.message;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class CartEvent {

	private String userId;
	private double amount;
	private String cartId;

}
