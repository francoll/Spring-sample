package com.fcmm.webstore.domain.repository;

import com.fcmm.webstore.domain.Cart;

public interface CartRepository {
	Cart create(Cart pCart);

	Cart read(String pCartId);

	void update(String pCartId, Cart pCart);

	void delete(String pCartId);
}
