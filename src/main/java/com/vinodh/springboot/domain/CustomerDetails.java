package com.vinodh.springboot.domain;

import java.util.Date;

import com.vinodh.springboot.entity.AppUser;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDetails {

	/* Customer */
	private Long appUserId;
	private String firstName;
	private String lastName;
	private String userName;
	private String userEmail;
	private String userPhone;
	/* Address */
	private String street;
	private String town;
	private String city;
	private String county;
	private String postcode;
	private AppUser customer;
	/* Parents */
	private String fatherName;
	private Long fatherPhone;
	private String motherName;
	private Long motherPhone;
	/* Orders */
	private long orderId;
	private double totPrice;
	private String orderDesc;
	private Date orderDt;
	/* Invoice */
	private long invoiceId;
	private double amountDue;
	private Date orderRaisedDt;
	private Date orderSettledDt;
	private Date orderCancelledDt;
	/* Products */
	private long prodId;
	private String prodName;
	private String prodDescription;
	private String price;
}
