package com.vinodh.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinodh.springboot.domain.CustomerDTO;
import com.vinodh.springboot.domain.CustomerDetails;
import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.repository.AppUserRepository;

@Service
public class CustomerDetailService implements UserDetailsService {

	@Autowired
	AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserRepository.findOne(Long.valueOf(username));
		CustomerDTO customerDTO = new CustomerDTO(appUser);
		List<String> roles = Stream.of("USER").collect(Collectors.toList());
		return new CustomerDetails(roles, customerDTO);
	}

}
