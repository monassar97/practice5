package com.training.ShoppingCart.repository.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.training.ShoppingCart.model.Status;

import lombok.Data;

@Data
@Entity
public class ShopCartEntity {
	@Id
	@NotEmpty
	private String id;
	private String Status;
	private double amount;
	private String date;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// @JoinColumn(name = "id")
	private List<ProductEntity> products;

}
