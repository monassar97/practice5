package com.training.ShoppingCart.adapter.rest;

import lombok.Data;

@Data
public class ProductDTO {

	private String id;
	private String name;
	private double price;
	private String imageUrl;
}
