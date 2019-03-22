package com.schoolofnet.RestApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolofnet.RestApi.model.Product;
import com.schoolofnet.RestApi.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repository;
	
	public ProductServiceImpl() {}
	
	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Product> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Product findOne(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public Product create(Product product) {
		return this.repository.save(product);
	}

	@Override
	public Product update(Long id, Product product) {
		Product productExists = this.repository.findOne(id);
		
		if (productExists != null) {
			product.setId(productExists.getId());
			this.repository.save(product);
			return product;
		}
		
		return null;
	}

	@Override
	public void delete(Long id) {
		Product product = this.repository.findOne(id);
		
		if (product != null) this.repository.delete(product);
		
	}

}
