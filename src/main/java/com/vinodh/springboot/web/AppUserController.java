package com.vinodh.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.service.AppUserService;

@RestController
@RequestMapping("/user")
public class AppUserController {

	@Autowired
	AppUserService appUserService;

	@GetMapping("/findAllUsers")
	public ResponseEntity<List<AppUser>> getUsers() {
		return ResponseEntity.ok(appUserService.getUsers());
	}
}
