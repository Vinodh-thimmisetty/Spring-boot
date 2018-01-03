package com.vinodh.springboot.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinodh.springboot.entity.AppUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

	// Any Constants has to go here
	@JsonProperty(value = "customerId")
	private Long customerId;
	@JsonProperty(value = "firstName")
	private String firstName;
	@JsonProperty(value = "lastName")
	private String lastName;
	@JsonProperty(value = "userName")
	private String userName;
	@JsonProperty(value = "userEmail")
	private String userEmail;
	@JsonProperty(value = "userPhone")
	private String userPhone;

	@JsonProperty(value = "address")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private AddressDTO address;

	@JsonProperty(value = "parents")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ParentsDTO parents;

	@JsonProperty(value = "orders")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<OrdersDTO> orders;

	@JsonProperty(value = "products")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ProductsDTO> products;

	/* Copy Constructor to fetch Convert Entity to DTO */
	public CustomerDTO(AppUser appUser) {
		if (Objects.nonNull(appUser)) {
			this.customerId = appUser.getAppUserId();
			this.firstName = appUser.getFirstName();
			this.lastName = appUser.getLastName();
			this.userName = appUser.getUserName();
			this.userEmail = appUser.getUserEmail();
			this.userPhone = appUser.getUserPhone();

			this.address = new AddressDTO(appUser.getAddress());

			if (Objects.nonNull(appUser.getParents())) {
				this.parents = new ParentsDTO(appUser.getParents());
			}

			if (CollectionUtils.isNotEmpty(appUser.getOrders())) {
				this.orders = appUser.getOrders().stream().map(eachOrder -> new OrdersDTO(eachOrder))
						.collect(Collectors.toList());
			}
			if (CollectionUtils.isNotEmpty(appUser.getOrders())) {
				this.products = appUser.getProducts().stream().map(eachProduct -> new ProductsDTO(eachProduct))
						.collect(Collectors.toList());
			}
		}
	}

}
