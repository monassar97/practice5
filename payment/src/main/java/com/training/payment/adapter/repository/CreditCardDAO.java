package com.training.payment.adapter.repository;

import java.util.List;

import com.training.payment.model.CreditCard;

public interface CreditCardDAO {
	public CreditCard createCard(CreditCard card);

	public List<CreditCard> getCards();

	public boolean cheacktransaction(String cardId, double recivedAmount);

}
