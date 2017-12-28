package com.vinodh.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {

	@Column(name = "STREET")
	private String street;
	@Column(name = "TOWN")
	private String town;
	@Column(name = "CITY", nullable = false)
	private String city;
	@Column(name = "COUNTRY", nullable = false)
	private String county;
	@Column(name = "ZIP_CODE", nullable = false)
	private String postcode;

}
