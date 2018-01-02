package com.vinodh.springboot.domain;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinodh.springboot.entity.AppUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

	/* Copy Constructor to fetch Convert Entity to DTO */
	public CustomerDTO(AppUser appUser) {
		if (Objects.nonNull(appUser)) {
			this.customerId = appUser.getAppUserId();
			this.firstName = appUser.getFirstName();
			this.lastName = appUser.getLastName();
			this.userName = appUser.getUserName();
			this.userEmail = appUser.getUserEmail();
			this.userPhone = appUser.getUserPhone();

			//this.address = new AddressDTO(appUser.getAddress());
			//this.parents = new ParentsDTO(appUser.getParents());

		}
	}

}
