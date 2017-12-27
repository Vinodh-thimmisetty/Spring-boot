package com.vinodh.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AppUser extends ResourceSupport {

	@Id
	private Long appUserId;
	private String userName;
	private String userEmail;
	private String userPhone;
}
