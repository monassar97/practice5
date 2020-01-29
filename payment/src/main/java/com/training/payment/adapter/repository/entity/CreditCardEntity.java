package com.training.payment.adapter.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class CreditCardEntity {
	@Id
	private String id;
	private double amount;
	private String userId;
}