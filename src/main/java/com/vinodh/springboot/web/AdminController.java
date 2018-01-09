package com.vinodh.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinodh.springboot.domain.AdminDTO;
import com.vinodh.springboot.service.impl.AdminDetailsService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminDetailsService adminDetailsService;

	@GetMapping("/hello")
	public String hello() {
		return "Hello Admin";
	}

	@GetMapping("/getAllAdminDetails")
	public ResponseEntity<List<AdminDTO>> getAllAdminDetails() {
		return ResponseEntity.ok(adminDetailsService.adminDetails());
	}

	@PostMapping("/createNewAdmin")
	public ResponseEntity<AdminDTO> createNewAdmin(@RequestBody AdminDTO admin) {
		return ResponseEntity.ok(adminDetailsService.createNewAdmin(admin));
	}

}
