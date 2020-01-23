package com.training.payment.adapter.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.payment.model.CreditCard;
import com.training.payment.service.CardService;

@RestController
@RequestMapping("cards")
public class CardController {
	private final CardService service;

	public CardController(CardService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public CreditCard createCard(@RequestBody CreditCard card) {
		return service.createCard(card);
	}
}
