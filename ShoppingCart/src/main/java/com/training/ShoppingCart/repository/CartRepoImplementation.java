package com.training.ShoppingCart.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.training.ShoppingCart.adapter.rest.ShopCartDTO;
import com.training.ShoppingCart.model.Product;
import com.training.ShoppingCart.model.ShopCart;
import com.training.ShoppingCart.model.Status;
import com.training.ShoppingCart.repository.entity.ProductEntity;
import com.training.ShoppingCart.repository.entity.ShopCartEntity;
import com.training.ShoppingCart.service.EventCart;

@Component
public class CartRepoImplementation implements ShopCartDAO {

	private final CartRepo repo;
	private final ModelMapper mapper;

	public CartRepoImplementation(CartRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public List<Product> getProducts(String id) {
		ShopCartEntity cart = repo.getOne(id);

		return cart.getProducts().stream().map(this::toProduct).collect(Collectors.toList());
	}

	@Override
	public Product addProduct(String id, Product product) {
		ShopCartEntity cart = repo.findById(id).get();
		List<ProductEntity> products = cart.getProducts();
		products.add(toProductEntity(product));
		cart.setAmount(calculateAmount(products));
		cart.setProducts(products);
		cart.setStatus(Status.SAVED);
		repo.save(cart);
		return product;
	}

	@Override
	public boolean removeProduct(String cartId, Product product) {
		if (!repo.existsById(cartId))
			return false;
		ShopCartEntity cart = repo.getOne(cartId);
		List<ProductEntity> products = cart.getProducts();
		products.remove(product);
		cart.setProducts(products);
		cart.setAmount(calculateAmount(products));
		cart.setStatus(Status.SAVED);
		repo.save(cart);
		return true;
	}

	@Override
	public boolean clearCart(String id) {
		if (!repo.existsById(id))
			return false;
		ShopCartEntity cart = repo.getOne(id);
		List<ProductEntity> products = cart.getProducts();
		products.clear();
		cart.setProducts(products);
		cart.setStatus(Status.CLEARED);
		repo.save(cart);
		return true;
	}

	@Override
	public void purchase(String id, String response) {
		ShopCartEntity cart = repo.getOne(id);

		cart.setStatus(Status.PENDING);
		if (response.equals("paid"))
			cart.setStatus(Status.PAID);
		if (response.equals("failed"))
			cart.setStatus(Status.FAILED);
		repo.save(cart);
	}

	@Override
	public double calculateAmount(List<ProductEntity> amounts) {
		double amount = 0.0;
		amount = amounts.stream().mapToDouble(x -> x.getPrice()).summaryStatistics().getCount();
		return amount;
	}

	@Override
	public void checkOut(String id) {
		ShopCartEntity cart = repo.findById(id).get();
		cart.setStatus(Status.CHECKOUT);
		repo.save(cart);
	}

	public Product toProduct(ProductEntity entity) {
		return mapper.map(entity, Product.class);
	}

	public ProductEntity toProductEntity(Product product) {
		return mapper.map(product, ProductEntity.class);
	}

	public ShopCartEntity toCartEntity(ShopCart cart) {
		return mapper.map(cart, ShopCartEntity.class);
	}

	@Override
	public ShopCart createCart(ShopCart cart) {
		System.out.println(cart.toString());
		repo.save(toCartEntity(cart));
		return cart;
	}

	public ShopCart toCart(ShopCartEntity cart) {
		return mapper.map(cart, ShopCart.class);
	}

	@Override
	public EventCart buy(String id) {
		ShopCart cart = toCart(repo.getOne(id));
		EventCart eCart = EventCart.builder().amount(cart.getAmount()).cartId(cart.getId()).userId(cart.getUserID())
				.build();
		return eCart;
	}

}