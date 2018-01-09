package com.vinodh.springboot.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinodh.springboot.domain.AdminDTO;
import com.vinodh.springboot.domain.AdminDetails;
import com.vinodh.springboot.entity.Admin;
import com.vinodh.springboot.repository.AdminRepository;

@Service
public class AdminDetailsService implements UserDetailsService {

	@Autowired
	AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.getAdminByName(username);
		if (Objects.isNull(admin)) {
			throw new UsernameNotFoundException(username);
		}
		AdminDTO adminDTO = new AdminDTO(admin);
		List<String> roles = Stream.of(admin.getAdminRole()).collect(Collectors.toList());
		return new AdminDetails(roles, adminDTO);
	}

	public List<AdminDTO> adminDetails() {
		return adminRepository.findAll().stream().map((eachAdmin) -> new AdminDTO(eachAdmin))
				.collect(Collectors.toList());
	}

	public AdminDTO createNewAdmin(AdminDTO admin) {
		return new AdminDTO(adminRepository.save(new Admin(admin)));
	}

}
