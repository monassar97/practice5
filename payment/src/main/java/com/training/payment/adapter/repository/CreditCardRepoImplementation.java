package com.training.payment.adapter.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.training.payment.adapter.repository.entity.CreditCardEntity;
import com.training.payment.model.CreditCard;

@Component
public class CreditCardRepoImplementation implements CreditCardDAO {
	private final CreditCardRepo repo;
	private final ModelMapper mapper;

	public CreditCardRepoImplementation(CreditCardRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public CreditCard createCard(CreditCard card) {
		return toCard(repo.save(toCreditEntity(card)));
	}

	@Override
	public List<CreditCard> getCards() {
		return repo.findAll().stream().map(this::toCard).collect(Collectors.toList());
	}

	public CreditCard toCard(CreditCardEntity entity) {
		return mapper.map(entity, CreditCard.class);
	}

	public CreditCardEntity toCreditEntity(CreditCard card) {
		return mapper.map(card, CreditCardEntity.class);
	}

	@Override
	public boolean cheacktransaction(String cardId, double recivedAmount) {
		return repo.getOne(cardId).getAmount() - recivedAmount >= 0 ? true : false;
	}
}