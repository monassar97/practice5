package com.training.ShoppingCart.adapter.rest;

import java.util.List;
import com.training.ShoppingCart.model.Product;
import com.training.ShoppingCart.model.Status;
import lombok.Data;

@Data
public class ShopCartDTO {
	private String id;
	private Status Status;
	private double amount;
	private String userID;
	private String date;
	private List<Product> products;
}
