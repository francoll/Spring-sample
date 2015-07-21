package com.fcmm.webstore.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -694354952032299587L;

	private String productId;

	public ProductNotFoundException(String pProductId) {
		this.productId = pProductId;
	}

	public String getProductId() {
		return productId;
	}

}
