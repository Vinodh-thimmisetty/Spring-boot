package com.vinodh.springboot.domain;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinodh.springboot.entity.Products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductsDTO {

 	@JsonProperty(value = "prodId")
	private long prodId;
	@JsonProperty(value = "prodName")
	private String prodName;
	@JsonProperty(value = "prodDescription")
	private String prodDescription;
	@JsonProperty(value = "price")
	private String price;

	/* Copy Constructor to fetch Convert Entity to DTO */
	public ProductsDTO(Products products) {
		if (Objects.nonNull(products)) { 
			this.prodId = products.getProdId();
			this.prodName = products.getProdName();
			this.prodDescription = products.getProdDescription();
			this.price = products.getPrice();
		}
	}
}
