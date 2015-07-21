package com.fcmm.webstore.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fcmm.webstore.domain.Product;

public interface ProductRepository {
	List <Product> getAllProducts();
	
	Product getProductById(String pProductID);
	
	List<Product> getProductsByCategory(String pCategory);
	
	Set<Product> getProductsByFilter(Map<String, List<String>> pFilterParams);
	
	void addProduct(Product pProduct);
}
