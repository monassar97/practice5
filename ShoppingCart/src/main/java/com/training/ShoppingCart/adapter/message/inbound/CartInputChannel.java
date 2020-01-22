package com.training.ShoppingCart.adapter.message.inbound;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CartInputChannel {
	String CHANNEL_NAME="cart-queue";
	
	@Input(CHANNEL_NAME)
	SubscribableChannel inChannel();
}
