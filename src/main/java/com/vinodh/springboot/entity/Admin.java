package com.vinodh.springboot.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.vinodh.springboot.domain.AdminDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "VINODH", name = "ADMIN_DETAILS") 
public class Admin {

	@Id
	@Column(name = "ADMIN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_ID_SEQ")
	@SequenceGenerator(schema = "VINODH", sequenceName = "ADMIN_ID_SEQ", initialValue = 1, name = "ADMIN_ID_SEQ", allocationSize = 1)
 	private long adminId;
	@Column(name = "ADMIN_USERNAME", nullable = false, unique = true)
	private String adminUserName;
	@Column(name = "ADMIN_PASSWORD")
	private String password;
	@Column(name = "ADMIN_FIRST_NAME")
	private String adminFirstName;
	@Column(name = "ADMIN_LAST_NAME")
	private String adminLastName;
	@Column(name = "ADMIN_EMAIL")
	private String adminEMail;
	@Column(name = "ADMIN_ROLE")
	private String adminRole;
	@Column(name = "APPLICATION_NAME")
	private String applicationName;

	/* Copy Constructor to fetch Convert DTO to Entity */
	public Admin(AdminDTO adminDTO) {
		if (Objects.nonNull(adminDTO)) {
			this.adminUserName = adminDTO.getAdminUserName();
			this.password = adminDTO.getPassword();
			this.adminFirstName = adminDTO.getAdminFirstName();
			this.adminLastName = adminDTO.getAdminLastName();
			this.adminEMail = adminDTO.getAdminEMail();
			this.adminRole = adminDTO.getAdminRole();
			this.applicationName = adminDTO.getApplicationName();

		}
	}
}
