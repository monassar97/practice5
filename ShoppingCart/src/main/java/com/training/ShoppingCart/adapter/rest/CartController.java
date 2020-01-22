package com.training.ShoppingCart.adapter.rest;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;
import org.springframework.ui.ModelMapExtensionsKt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.ShoppingCart.model.Product;
import com.training.ShoppingCart.model.ShopCart;
import com.training.ShoppingCart.service.CartService;

@RestController
@RequestMapping("/shop")
public class CartController {

	private final CartService service;
	private final ModelMapper mapper;

	public CartController(CartService service, ModelMapper mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}

	@PostMapping("/create")
	public ShopCartDTO createCart(@RequestBody ShopCartDTO cart) {
		return toCartDto(service.createCart(cart));
	}

	@GetMapping("/products/{cartId}")
	public List<ProductDTO> getAllProducts(@PathVariable("cartId") String cartId) {
		return service.getAll(cartId);
	}

	@DeleteMapping("/{cartId}")
	public boolean deleteProduct(@RequestParam("cartId") String id, @RequestBody ProductDTO product) {
		return service.removProductFromCart(id, product);
	}

	@PostMapping("/{cartId}")
	public ProductDTO addProduct(@RequestParam("cartId") String id, @RequestBody ProductDTO product) {
		return toProductDto(service.addProductToCart(id, product));
	}

	@GetMapping("/clear/{cartId}")
	public boolean clear(@PathVariable("cartId") String cartId) {
		return service.clearCart(cartId);
	}

	@GetMapping("/checkout/{id}")
	public void checkOut(@PathVariable("id") String id) {
		service.CheckOut(id);
	}

	@GetMapping("/buy/{id}")
	public void buy(@PathVariable("id") String id) {
		service.buy(id);
	}

	public ShopCartDTO toCartDto(ShopCart cart) {
		return mapper.map(cart, ShopCartDTO.class);
	}

	public ProductDTO toProductDto(Product product) {
		return mapper.map(product, ProductDTO.class);
	}
}
