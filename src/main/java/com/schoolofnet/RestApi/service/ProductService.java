package com.schoolofnet.RestApi.service;

import java.util.List;

import com.schoolofnet.RestApi.model.Product;


public interface ProductService {
	
	public List<Product> findAll();
	public Product findOne(Long id);
	public Product create(Product product);
	public Product update(Long id, Product product);
	public void delete(Long id);
	
	
}
