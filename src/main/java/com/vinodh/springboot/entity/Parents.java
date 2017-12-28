package com.vinodh.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Table(schema = "VINODH", name = "PARENTS")
public class Parents implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 222056424508901935L;

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "parents"))
	@GeneratedValue(generator = "generator")
	@Column(name = "CUST_ID", unique = true, nullable = false, precision = 22, scale = 0)
	@JsonIgnore
	private Long appUserId;
	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	@JsonIgnore
	private AppUser customer;
	@Column(name = "FATHER_NAME", length = 50)
	private String fatherName;
	@Column(name = "FATHER_PHONE", precision = 22, scale = 0)
	private Long fatherPhone;
	@Column(name = "MOTHER_NAME", precision = 22, scale = 0)
	private String motherName;
	@Column(name = "MOTHER_PHONE", precision = 22, scale = 0)
	private Long motherPhone;
}