package com.training.ShoppingCart.adapter.message.outbound;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CartoutputChannel {
	String CHANNEL_NAME = "cart-events";

	@Output(CHANNEL_NAME)
	MessageChannel outChannel();
}
