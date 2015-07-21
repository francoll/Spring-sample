package com.fcmm.webstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcmm.webstore.domain.Product;
import com.fcmm.webstore.domain.repository.ProductRepository;
import com.fcmm.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}
	
	@Override
	public Product getProductById(String pProductID) {
		return productRepository.getProductById(pProductID);
	}

	@Override
	public List<Product> getProductsByCategory(String pCategory) {
		return productRepository.getProductsByCategory(pCategory);
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> pFilterParams) {
		return productRepository.getProductsByFilter(pFilterParams);
	}

	@Override
	public void addProduct(Product pProduct) {
		productRepository.addProduct(pProduct);
	}
	
	
}
