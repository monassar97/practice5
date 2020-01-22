package com.training.ShoppingCart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.ShoppingCart.model.ShopCart;
import com.training.ShoppingCart.repository.entity.ShopCartEntity;

@Repository
public interface CartRepo extends JpaRepository<ShopCartEntity, String> {

}
