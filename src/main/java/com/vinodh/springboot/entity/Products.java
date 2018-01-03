package com.vinodh.springboot.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinodh.springboot.domain.ProductsDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
@Table(schema = "VINODH", name = "PRODUCTS")
public class Products {

	@Id
	@Column(name = "PROD_ID", columnDefinition = "Unique Id Generated per PRODUCT", nullable = false, length = 50, unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
	@SequenceGenerator(schema = "VINODH", sequenceName = "PRODUCT_SEQ", initialValue = 1, name = "PRODUCT_SEQ", allocationSize = 1)
	private long prodId;
	@Column(name = "PROD_NAME", nullable = false, length = 50)
	private String prodName;
	@Column(name = "PROD_DESC", length = 200)
	private String prodDescription;
	@Column(name = "REGULAR_PRICE", precision = 2)
	private String price;
	@Column(name = "LAST_UPDATED_TIME")
	private Date updatedTime;

	@ManyToMany(mappedBy = "productsList", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Orders> orderList;

	// Here Products is OWNING Side and maps customer user id as foreign key
	@ManyToOne(optional = false)
	@JoinColumn(name = "CUST_ID", referencedColumnName = "CUST_ID")
	@JsonIgnore
	private AppUser customer;

	/* Copy Constructor to fetch Convert DTO to Entity */
	public Products(ProductsDTO productsDTO) {
		if (Objects.nonNull(productsDTO)) { 
			this.prodId = productsDTO.getProdId();
			this.prodName = productsDTO.getProdName();
			this.prodDescription = productsDTO.getProdDescription();
			this.price = productsDTO.getPrice();

		}
	}

}