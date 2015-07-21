package com.fcmm.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.fcmm.webstore.domain.Product;
import com.fcmm.webstore.exception.ProductNotFoundException;
import com.fcmm.webstore.service.ProductService;

public class ProductIdValidator implements ConstraintValidator<ProductId, String> {

	@Autowired
	private ProductService productService;

	public void initialize(ProductId pConstraintAnnotation) {
		// intentionally left blank; this is the place to initialize the
		// constraint annotation for any sensible default values.
	}

	public boolean isValid(String pValue, ConstraintValidatorContext pContext) {
		Product product;
		try {
			product = productService.getProductById(pValue);

		} catch (ProductNotFoundException e) {
			return true;
		}

		if (product != null) {
			return false;
		}

		return true;
	}
}
