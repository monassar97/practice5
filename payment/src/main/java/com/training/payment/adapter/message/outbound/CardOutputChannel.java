package com.training.payment.adapter.message.outbound;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CardOutputChannel {
	public String CHANNEL_NAME = "card-events";

	@Output(CHANNEL_NAME)
	MessageChannel outChannel();
}
