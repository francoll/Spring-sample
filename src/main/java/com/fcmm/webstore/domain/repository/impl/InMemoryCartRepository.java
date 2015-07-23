package com.fcmm.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fcmm.webstore.domain.Cart;
import com.fcmm.webstore.domain.repository.CartRepository;

@Repository
public class InMemoryCartRepository implements CartRepository {

	private Map<String, Cart> listOfCarts;

	public InMemoryCartRepository() {
		listOfCarts = new HashMap<String, Cart>();

	}

	public Cart create(Cart pCart) {
		if (listOfCarts.keySet().contains(pCart.getCartId())) {
			throw new IllegalArgumentException(String
					.format("Can not create a cart. A cart with the given id (%) aldrady exists", pCart.getCartId()));
		}

		listOfCarts.put(pCart.getCartId(), pCart);
		return pCart;
	}

	public Cart read(String pCartId) {
		return listOfCarts.get(pCartId);
	}

	public void update(String pCartId, Cart pCart) {
		if (!listOfCarts.keySet().contains(pCartId)) {
			throw new IllegalArgumentException(String
					.format("Can not update cart. The cart with the given id (%) does not exist", pCartId));
		}

		listOfCarts.put(pCartId, pCart);
	}

	public void delete(String pCartId) {
		if (!listOfCarts.keySet().contains(pCartId)) {
			throw new IllegalArgumentException(String
					.format("Can not delete cart. The cart with the give id (%) does not does not exist", pCartId));
		}

		listOfCarts.remove(pCartId);
	}

}
