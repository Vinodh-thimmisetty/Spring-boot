package com.vinodh.springboot.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(schema = "VINODH", name = "ORDERS")
public class Orders {
	@Id
	@Column(name = "ORDER_ID", columnDefinition = "Unique Id Generated per ORDERS", nullable = false, length = 50, unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQ")
	@SequenceGenerator(schema = "VINODH", sequenceName = "ORDERS_SEQ", initialValue = 1, name = "ORDERS_SEQ", allocationSize = 1)
	private long orderId;
	@Column(name = "CUST_ID")
	private long appUserId;
	@Column(name = "TOTAL_PRICE", precision = 2)
	private double totPrice;
	@Column(name = "OREDER_DESC")
	private String orderDesc;
	@Column(name = "ORDER_DATE")
	private Date orderDt;
	
	// optional = false :: This becomes INNER Join. For Outer join optional has to be TRUE(default value)
	// cascade = Say on persist of Parent, what should happen to Child Object(Joined table) linked to that??
	//            E.g: Performing CRUD operations on the Orders entity and want to propagate the same operations to the child OrderInvoice object
	// mappedBy = reference variable in joined Object 
	// targetEntity = To which entity mapping is done
	@OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "order", targetEntity = OrderInvoice.class)
	private OrderInvoice invoice;

	// Here Order is OWNING Side and maps customer user id as foreign key
	@ManyToOne(optional = false)
	@JoinColumn(name = "appUserId", referencedColumnName = "appUserId")
	private AppUser customer;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "OrderDetails", 
			   joinColumns = @JoinColumn(
					   				name = "orderId", referencedColumnName = "prodId"),
			   inverseJoinColumns = @JoinColumn(
					   				name = "prodId", referencedColumnName = "orderId"))
	private List<Products> productsList;
}
