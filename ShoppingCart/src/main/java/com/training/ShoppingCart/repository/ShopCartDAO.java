package com.training.ShoppingCart.repository;

import java.util.List;

import com.training.ShoppingCart.model.Product;
import com.training.ShoppingCart.model.ShopCart;
import com.training.ShoppingCart.repository.entity.ProductEntity;
import com.training.ShoppingCart.repository.entity.ShopCartEntity;
import com.training.ShoppingCart.service.EventCart;

public interface ShopCartDAO {

	public ShopCart createCart(ShopCart cart);

	public List<Product> getProducts(String id);

	public Product addProduct(String id, Product product);

	public boolean removeProduct(String cartId, Product productId);

	public boolean clearCart(String id);

	public void purchase(String id, String Response);

	public double calculateAmount(List<ProductEntity> amounts);

	public void checkOut(String id);

	public EventCart buy(String id);
}
