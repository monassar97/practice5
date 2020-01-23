package com.training.payment.adapter.message.inbound;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CardInputChannel {
	public String CHANNEL_NAME = "card-queue";

	@Input(CHANNEL_NAME)
	SubscribableChannel inputChannel();
}
