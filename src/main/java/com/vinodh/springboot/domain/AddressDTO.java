package com.vinodh.springboot.domain;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinodh.springboot.entity.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

	@JsonProperty(value = "street")
	private String street;
	@JsonProperty(value = "town")
	private String town;
	@JsonProperty(value = "city")
	private String city;
	@JsonProperty(value = "county")
	private String county;
	@JsonProperty(value = "postcode")
	private String postcode;

	/* Copy Constructor to fetch Convert Entity to DTO */
	public AddressDTO(Address address) {
		if (Objects.nonNull(address)) {
			this.street = address.getStreet();
			this.town = address.getTown();
			this.city = address.getCity();
			this.county = address.getCounty();
			this.postcode = address.getPostcode();
		}
	}
}
