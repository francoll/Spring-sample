package com.fcmm.webstore.service;

import com.fcmm.webstore.domain.Cart;

public interface CartService {
	Cart create(Cart pCart);

	Cart read(String pCartId);

	void update(String pCartId, Cart pCart);

	void delete(String pCartId);
}
