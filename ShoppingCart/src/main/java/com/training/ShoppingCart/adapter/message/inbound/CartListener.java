package com.training.ShoppingCart.adapter.message.inbound;

import org.springframework.cloud.stream.annotation.StreamListener;

import com.training.ShoppingCart.service.CartService;
import com.training.ShoppingCart.service.ResponseEvent;

public class CartListener {
	private final CartService service;

	public CartListener(CartService service) {
		super();
		this.service = service;
	}

	@StreamListener(CartInputChannel.CHANNEL_NAME)
	public void reciveMessage(ResponseEvent reply) {
		service.addStatus(reply);
	}
}
