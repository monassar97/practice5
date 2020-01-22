package com.training.ShoppingCart.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ShopCart {
	@Id
	@NotEmpty
	private String id;
	private Status Status;
	private double amount;
	private String userID;
	private String date;
	private List<Product> products;

}
