package com.training.payment.adapter.message.inbound;

import org.springframework.cloud.stream.annotation.StreamListener;
import com.training.payment.adapter.message.CartEvent;
import com.training.payment.service.CardService;

public class CardListener {
	private final CardService service;

	public CardListener(CardService service) {
		super();
		this.service = service;
	}

	@StreamListener(CardInputChannel.CHANNEL_NAME)
	public void subscride(CartEvent event) {
		System.out.println(event.toString());
		service.checkamount(event);
	}
}