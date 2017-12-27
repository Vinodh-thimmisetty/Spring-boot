package com.vinodh.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.service.AppUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Description("CRUD Operations for Simple Application user")
@ExposesResourceFor(AppUser.class)
@Slf4j
public class AppUserController {

	@Autowired
	AppUserService appUserService;

	@Autowired
	EntityLinks entityLinks;

	@GetMapping(path = "/findAllUsers")
	public ResponseEntity<List<AppUser>> getUsers() {
		return ResponseEntity.ok(appUserService.getUsers());
	}

	@GetMapping(path = "/findAllUsers/hateos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<AppUser>> getUsersHateaos() {
		log.info("Buildng Resource Links");
		Resources<AppUser> resources = new Resources<>(appUserService.getUsers());
		resources.add(this.entityLinks.linkToCollectionResource(AppUser.class));
		return ResponseEntity.ok(resources);
	}

	@GetMapping("/findAllUsers/paginatation")
	public ResponseEntity<Page<AppUser>> getUsersWIthPagination(
			@RequestParam(required = false, name = "startIndex", defaultValue = "0") int startIndex,
			@RequestParam(required = false, name = "endIndex", defaultValue = "10") int endIndex,
			@RequestParam(required = false, name = "limit", defaultValue = "10") int limit,
			@RequestParam(required = false, name = "sortby", defaultValue = "appUserId") String sortby,
			@RequestParam(required = false, name = "sortDir", defaultValue = "ASC") String sortDir) {
		return ResponseEntity.ok(appUserService.getUsersWIthPagination(new PageRequest(startIndex, limit,
				("DESC".equalsIgnoreCase(sortDir)) ? Sort.Direction.DESC : Sort.Direction.ASC, sortby)));
	}

	@GetMapping("/findUser/{appuserId:[0-9]+}")
	public ResponseEntity<AppUser> getUserById(@PathVariable("appuserId") Long appuserId) {
		return ResponseEntity.ok(appUserService.getUserById(appuserId));
	}

	@GetMapping("/findUser/{appuserName:[a-zA-Z]+}")
	public ResponseEntity<List<AppUser>> getUserByName(@PathVariable("appuserName") String appuserName) {
		return ResponseEntity.ok(appUserService.getUserByName(appuserName));
	}

	@GetMapping("/findUserbyName/{appuserName}")
	public ResponseEntity<List<AppUser>> getUserByName1(@PathVariable("appuserName") String appuserName) {
		return ResponseEntity.ok(appUserService.getUserByName(appuserName));
	}
}
