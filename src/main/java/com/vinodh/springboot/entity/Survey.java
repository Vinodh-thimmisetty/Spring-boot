package com.vinodh.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(schema = "VINODH", name = "PRODUCT_SURVEY")
public class Survey {
	@Id
	@Column(name = "SURVEY_ID", columnDefinition = "Unique Id Generated per PRODUCT", nullable = false, length = 50, unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SURVEY_SEQ")
	@SequenceGenerator(schema = "VINODH", sequenceName = "PRODUCT_SURVEY_SEQ", initialValue = 1, name = "PRODUCT_SURVEY_SEQ", allocationSize = 1)

	private long surveyId;

	@Column(name = "PROD_ID", nullable = false)
	private long prodId;

	@Column(name = "SURVEY_RESULT", nullable = false, length = 4000)
	private String surveyResult;

	@Column(name = "YEAR_OF_SURVEY")
	private int year;

	@Column(name = "LAST_UPDATED_TIME")
	private Date updatedTime;

}