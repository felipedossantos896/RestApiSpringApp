package com.schoolofnet.RestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolofnet.RestApi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
