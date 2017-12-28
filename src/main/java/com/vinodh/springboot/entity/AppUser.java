package com.vinodh.springboot.entity;

import java.util.List;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

	// One-to-One Mapping Sample
	@OneToOne(mappedBy = "customer", targetEntity = Parents.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private Parents parents;

	// One-to-Many Mapping Sample
	@OneToMany(mappedBy = "customer", targetEntity = Orders.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<Orders> orders;

	// One-to-Many Mapping Sample
	@OneToMany(mappedBy = "customer", targetEntity = Products.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<Products> products;

}
