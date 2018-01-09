package com.vinodh.springboot.domain;

import java.util.Objects;

import com.vinodh.springboot.entity.Admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDTO {
	private long adminId;
	private String adminUserName;
	private String password;
	private String adminFirstName;
	private String adminLastName;
	private String adminEMail;
	private String adminRole;
	private String applicationName;

	/* Copy Constructor to fetch Convert Entity to DTO */
	public AdminDTO(Admin admin) {
		if (Objects.nonNull(admin)) {
			this.adminId = admin.getAdminId();
			this.adminUserName = admin.getAdminUserName();
			this.password = admin.getPassword();
			this.adminFirstName = admin.getAdminFirstName();
			this.adminLastName = admin.getAdminLastName();
			this.adminEMail = admin.getAdminEMail();
			this.adminRole = admin.getAdminRole();
			this.applicationName = admin.getApplicationName();

		}
	}
}
