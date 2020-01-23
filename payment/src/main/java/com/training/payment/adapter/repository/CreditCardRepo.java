package com.training.payment.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.payment.adapter.repository.entity.CreditCardEntity;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCardEntity, String> {

}
