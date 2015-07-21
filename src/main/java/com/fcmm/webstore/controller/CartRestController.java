package com.fcmm.webstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fcmm.webstore.domain.Cart;
import com.fcmm.webstore.domain.CartItem;
import com.fcmm.webstore.domain.Product;
import com.fcmm.webstore.exception.ProductNotFoundException;
import com.fcmm.webstore.service.CartService;
import com.fcmm.webstore.service.ProductService;

@Controller
@RequestMapping(value = "rest/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/{cartId}", method = RequestMethod.POST)
	public @ResponseBody Cart create(@RequestBody Cart pCart) {
		return cartService.create(pCart);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value = "cartId") String pCartId) {
		return cartService.read(pCartId);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable(value = "cartId") String pCartId, @RequestBody Cart pCart) {
		cartService.update(pCartId, pCart);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "cartId") String pCartId) {
		cartService.delete(pCartId);
	}

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable String pProductId, HttpServletRequest pRequest) {

		String sessionId = pRequest.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if (cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}

		Product product = productService.getProductById(pProductId);
		if (product == null) {
			throw new IllegalArgumentException(new ProductNotFoundException(pProductId));
		}

		cart.addCartItem(new CartItem(product));

		cartService.update(sessionId, cart);
	}

	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable String pProductId, HttpServletRequest pRequest) {

		String sessionId = pRequest.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if (cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}

		Product product = productService.getProductById(pProductId);
		if (product == null) {
			throw new IllegalArgumentException(new ProductNotFoundException(pProductId));
		}

		cart.removeCartItem(new CartItem(product));

		cartService.update(sessionId, cart);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload")
	public void handleClientErrors(Exception ex) {
		// no op
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
	public void handleServerErrors(Exception ex) {
		// no op
	}
}
