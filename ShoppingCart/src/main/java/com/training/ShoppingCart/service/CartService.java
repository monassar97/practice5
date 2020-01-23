package com.training.ShoppingCart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.training.ShoppingCart.adapter.message.inbound.CartInputChannel;
import com.training.ShoppingCart.adapter.message.inbound.CartListener;
import com.training.ShoppingCart.adapter.message.outbound.CartoutputChannel;
import com.training.ShoppingCart.adapter.rest.ProductDTO;
import com.training.ShoppingCart.adapter.rest.ShopCartDTO;
import com.training.ShoppingCart.model.Product;
import com.training.ShoppingCart.model.ShopCart;
import com.training.ShoppingCart.model.Status;
import com.training.ShoppingCart.repository.CartRepoImplementation;
import com.training.ShoppingCart.repository.entity.ShopCartEntity;

@Service
public class CartService {
	private final ModelMapper mapper;
	private final CartRepoImplementation repo;
	private final CartoutputChannel outputChannel;

	public CartService(ModelMapper mapper, CartRepoImplementation repo, CartoutputChannel outputChannel) {
		super();
		this.mapper = mapper;
		this.repo = repo;
		this.outputChannel = outputChannel;

	}

	public ShopCart createCart(ShopCartDTO cart) {
		return repo.createCart(toCart(cart));
	}

	public void buy(String cartId) {
		userServiceMessageSender(repo.buy(cartId));
	}

	public boolean userServiceMessageSender(EventCart cart) {
		Message<EventCart> message = MessageBuilder.withPayload(cart).build();

		return outputChannel.outChannel().send(message);
	}

	public List<ProductDTO> getAll(String id) {
		return repo.getProducts(id).stream().map(this::toProductDto).collect(Collectors.toList());
	}

	public Product addProductToCart(String cartId, ProductDTO dto) {
		return repo.addProduct(cartId, toProduct(dto));
	}

	public boolean removProductFromCart(String cartId, ProductDTO productId) {
		return repo.removeProduct(cartId, toProduct(productId));
	}

	public boolean clearCart(String cartId) {
		return repo.clearCart(cartId);
	}

	public String CheckOut(String id) {
		repo.checkOut(id);
		return "Checked Out";
	}

	public void addStatus(ResponseEvent reply) {
		repo.purchase(reply.getCartId(), reply.getReply());
	}

	public String cartSatus(String cartId) {
		return repo.getStatus(cartId);
	}

	public Product toProduct(ProductDTO dto) {
		return mapper.map(dto, Product.class);
	}

	public ProductDTO toProductDto(Product product) {
		return mapper.map(product, ProductDTO.class);
	}

	public ShopCart toCart(ShopCartDTO cart) {
		return mapper.map(cart, ShopCart.class);
	}

	public ShopCartDTO toCartDto(ShopCart cart) {
		return mapper.map(cart, ShopCartDTO.class);
	}

}
