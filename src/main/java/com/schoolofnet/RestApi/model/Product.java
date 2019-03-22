package com.schoolofnet.RestApi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Can not be empty")
	@NotBlank(message = "Can not be blank")
	@Size(min = 4, max = 10)
	private String name;

	@NotNull(message = "Amount can not be null")
	@Min(value = 1, message = "Quantity must be greater than 0")
	@Max(value = 1000, message = "Quantity must be less than 1001")
	private Integer quantidade;
	
	private Date dateCreated;

	public Product() {}
	
	public Product(String name, Integer quantidade) {
		this.name = name;
		this.quantidade = quantidade;
	}

	@PrePersist
	private void prePersist() {
		if (this.dateCreated == null) {
			this.dateCreated = new Date();
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Override
	public String toString() {
		return "{id} " + this.id + "{name} " + this.name + "{quantidade} " + this.quantidade + "{date} " + this.dateCreated;
	}
	
}
