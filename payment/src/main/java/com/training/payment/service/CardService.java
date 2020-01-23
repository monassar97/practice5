package com.training.payment.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.training.payment.adapter.message.CartEvent;
import com.training.payment.adapter.message.inbound.CardInputChannel;
import com.training.payment.adapter.message.outbound.CardOutputChannel;
import com.training.payment.adapter.repository.CreditCardRepoImplementation;
import com.training.payment.model.CreditCard;

@Service
public class CardService {
	private final CardInputChannel inChannel;
	private final CardOutputChannel outChannel;
	private final CreditCardRepoImplementation repo;

	public CardService(CardInputChannel inChannel, CardOutputChannel outChannel, CreditCardRepoImplementation repo) {
		super();
		this.inChannel = inChannel;
		this.outChannel = outChannel;
		this.repo = repo;
	}

	public boolean checkamount(CartEvent event) {
		String result = repo.cheacktransaction(event.getUserId(), event.getAmount()) ? "succeed" : "failed";
		Message<String> message = MessageBuilder.withPayload(result).build();
		outChannel.outChannel().send(message);
		return repo.cheacktransaction(event.getUserId(), event.getAmount());
	}

	public CreditCard createCard(CreditCard card) {
		return repo.createCard(card);
	}
}
