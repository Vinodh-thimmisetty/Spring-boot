package com.vinodh.springboot.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "VINODH",name = "ORDER_INVOICE")
public class OrderInvoice {
	@Id
	@Column(name = "INVOICE_ID", columnDefinition = "Unique Id Generated per PRODUCT", nullable = false, length = 50, unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_INVOICE_SEQ")
	@SequenceGenerator(schema = "VINODH", sequenceName = "ORDER_INVOICE_SEQ", initialValue = 1, name = "ORDER_INVOICE_SEQ", allocationSize = 1)
	private long invoiceId; 
	@Column(name = "AMOUNT_DUE", precision = 2)
	private double amountDue;
	@Column(name = "DATE_RAISED") 
	private Date orderRaisedDt;
	@Column(name = "DATE_SETTLED") 
	private Date orderSettledDt;
	@Column(name = "DATE_CANCELLED")
	private Date orderCancelledDt;
	@Version
	@Column(name = "LAST_UPDATED_TIME")
	private Date updatedTime;

	@OneToOne // This becomes INNER Join. For Outer join optional has to be TRUE(default value)
	@JoinColumn(name = "ORDER_ID", referencedColumnName="ORDER_ID") // ON condition variable(primary/foreign keys of joining tables) 
	@JsonIgnore
	private Orders order;
}