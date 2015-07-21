package com.fcmm.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcmm.webstore.domain.Product;
import com.fcmm.webstore.service.OrderService;
import com.fcmm.webstore.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductService productService;

	@Override
	public void processOrder(String pProductId, long pQuantity) {
		Product productById = productService.getProductById(pProductId);

		if (productById.getUnitsInStock() < pQuantity) {
			throw new IllegalArgumentException(
					"Out of Stock. Available Units in stock: " + productById.getUnitsInStock());
		}

		productById.setUnitsInStock(productById.getUnitsInStock() - pQuantity);
	}
}
