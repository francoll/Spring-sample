package com.fcmm.webstore.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fcmm.webstore.domain.Product;

@Component
public class UnitsInStockValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object pTarget, Errors pErrors) {
		Product product = (Product) pTarget;

		if (product.getUnitPrice() != null && new BigDecimal(10000).compareTo(product.getUnitPrice()) <= 0
				&& product.getUnitsInStock() > 99) {
			pErrors.rejectValue("unitsInStock", "com.fcmm.webstore.validator.UnitsInStockValidator.message");
		}
	}

}
