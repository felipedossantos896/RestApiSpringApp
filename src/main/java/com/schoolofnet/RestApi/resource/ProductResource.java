package com.schoolofnet.RestApi.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.schoolofnet.RestApi.model.Product;
import com.schoolofnet.RestApi.service.ProductService;

@Api(value = "API REST - Model Product")
@RestController
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	public ProductResource() {}
	
	public ProductResource(ProductService service) {
		this.service = service;
	}
	
	@ApiOperation(value = "Find all products in database")
	@GetMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		List<Product> list = this.service.findAll();

		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find by id in database")
	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<?> findOne(@PathVariable(value = "id") Long id) {
		Product product = this.service.findOne(id);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create a new product")
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {
		if (!errors.hasErrors()) {
			Product productCreated = this.service.create(product);
			return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
		}
		
		return ResponseEntity
					.badRequest()
					.body(errors
							.getAllErrors()
							.stream()
							.map(msg -> msg.getDefaultMessage())
							.collect(Collectors.joining(",")));
	}
	
	@ApiOperation(value = "Update a product by id")
	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Product product, Errors errors) {
		if (!errors.hasErrors()) {
			Product productUpdated = this.service.update(id, product);

			return new ResponseEntity<Product>(productUpdated, HttpStatus.OK);
		}

		return ResponseEntity
					.badRequest()
					.body(errors
							.getAllErrors()
							.stream()
							.map(msg -> msg.getDefaultMessage())
							.collect(Collectors.joining(",")));
	}
	
	@ApiOperation(value = "Delete product by id")
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") Long id) {
		this.service.delete(id);
	}
	
}
