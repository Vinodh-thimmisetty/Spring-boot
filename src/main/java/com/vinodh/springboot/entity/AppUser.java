package com.vinodh.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "VINODH", name = "CUSTOMER")
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

	@OneToMany(mappedBy = "customer", targetEntity = Orders.class, fetch = FetchType.EAGER)
	private Orders orders;

	@OneToMany(mappedBy = "customer", targetEntity = Products.class, fetch = FetchType.EAGER)
	private Products products;

}
