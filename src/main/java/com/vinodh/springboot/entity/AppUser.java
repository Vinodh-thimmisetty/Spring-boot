package com.vinodh.springboot.entity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.vinodh.springboot.domain.CustomerDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// @AllArgsConstructor
// @Builder
@Entity
@Table(schema = "VINODH", name = "CUSTOMER")
@JsonInclude(content = Include.NON_NULL)
public class AppUser {

	@Id
	@Column(name = "CUST_ID", columnDefinition = "Unique Id Generated per Customer", nullable = false, length = 50, unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
	@SequenceGenerator(schema = "VINODH", sequenceName = "CUSTOMER_SEQ", initialValue = 1, name = "CUSTOMER_SEQ", allocationSize = 1)
	private Long appUserId;

	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	private String lastName;

	@Column(name = "USER_NAME", nullable = false, length = 50)
	private String userName;

	@Column(name = "EMAIL_ADDRESS", nullable = false)
	private String userEmail;

	@Column(name = "PHONE_NUMBER", nullable = false)
	private String userPhone;

	@Embedded
	private Address address;

	// One-to-One Mapping Sample
	@OneToOne(mappedBy = "customer", targetEntity = Parents.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Parents parents;

	// One-to-Many Mapping Sample
	@OneToMany(mappedBy = "customer", targetEntity = Orders.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<Orders> orders;

	// One-to-Many Mapping Sample
	@OneToMany(mappedBy = "customer", targetEntity = Products.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<Products> products;

	/* Copy Constructor to fetch Convert DTO to Entity */
	public AppUser(CustomerDTO customerInfo) {
		if (Objects.nonNull(customerInfo)) {
			this.firstName = customerInfo.getFirstName();
			this.lastName = customerInfo.getLastName();
			this.userName = customerInfo.getUserName();
			this.userEmail = customerInfo.getUserEmail();
			this.userPhone = customerInfo.getUserPhone();

			this.address = new Address(customerInfo.getAddress());

			if (Objects.nonNull(customerInfo.getParents())) {
				this.parents = new Parents(customerInfo.getParents());
			}

			if (CollectionUtils.isNotEmpty(customerInfo.getOrders())) {
				this.orders = customerInfo.getOrders().stream().map(eachOrder -> new Orders(eachOrder))
						.collect(Collectors.toList());
			}
			if (CollectionUtils.isNotEmpty(customerInfo.getOrders())) {
				this.products = customerInfo.getProducts().stream().map(eachProduct -> new Products(eachProduct))
						.collect(Collectors.toList());
			}
		}
	}

}
