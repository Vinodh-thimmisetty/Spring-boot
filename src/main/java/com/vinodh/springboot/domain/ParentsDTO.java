package com.vinodh.springboot.domain;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinodh.springboot.entity.Parents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParentsDTO {

	@JsonProperty(value = "customerId")
	private Long customerId;
	@JsonProperty(value = "fatherName")
	private String fatherName;
	@JsonProperty(value = "fatherPhone")
	private Long fatherPhone;
	@JsonProperty(value = "motherName")
	private String motherName;
	@JsonProperty(value = "motherPhone")
	private Long motherPhone;

	/* Copy Constructor to fetch Convert Entity to DTO */
	public ParentsDTO(Parents parents) {
		if (Objects.nonNull(parents)) {
			this.customerId = parents.getAppUserId();
			this.fatherName = parents.getFatherName();
			this.fatherPhone = parents.getFatherPhone();
			this.motherName = parents.getMotherName();
			this.motherPhone = parents.getMotherPhone();
		}
	}
}
