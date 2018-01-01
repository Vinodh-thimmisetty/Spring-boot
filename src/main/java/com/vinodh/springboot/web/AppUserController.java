package com.vinodh.springboot.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.entity.Orders;
import com.vinodh.springboot.entity.Parents;
import com.vinodh.springboot.entity.Products;
import com.vinodh.springboot.service.AppUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Description("CRUD Operations for Simple Application user")
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
	public ResponseEntity<List<Resource<AppUser>>> getUsersHateaos() {
		log.info("Buildng Resource Links");
		List<AppUser> appUsers = appUserService.getUsers();
		List<Resource<AppUser>> resources = new ArrayList<>();
		for (AppUser appUser : appUsers) {
			resources.add(buildCustomerResources(appUser));
		}

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

	@GetMapping("/findUser/hateos/{appuserId:[0-9]+}")
	public ResponseEntity<AppUser> getUserByIdHateaos(@PathVariable("appuserId") Long appuserId) {
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

	@GetMapping("/findParent/{appuserId:[0-9]+}")
	public ResponseEntity<Parents> findParent(@PathVariable("appuserId") Long appuserId) {
		return ResponseEntity.ok(appUserService.getUserById(appuserId).getParents());
	}

	@GetMapping("/findOrders/{appuserId:[0-9]+}")
	public ResponseEntity<List<Orders>> findOrders(@PathVariable("appuserId") Long appuserId) {
		return ResponseEntity.ok(appUserService.getUserById(appuserId).getOrders());
	}

	@GetMapping("/findProducts/{appuserId:[0-9]+}")
	public ResponseEntity<List<Products>> findProducts(@PathVariable("appuserId") Long appuserId) {
		return ResponseEntity.ok(appUserService.getUserById(appuserId).getProducts());
	}

	@PostMapping("/addNewCustomer")
	public ResponseEntity<AppUser> addNewCustomer(@RequestBody AppUser appUser) {
		return ResponseEntity.ok(appUserService.saveCustomer(appUser));
	}
	
	@PostMapping("/addNewParents")
	public ResponseEntity<AppUser> addNewParents(@RequestBody Parents parents) {
		AppUser appUser = appUserService.getUserById(parents.getAppUserId());
		parents.setCustomer(appUser);
		appUser.setParents(parents);
		return ResponseEntity.ok(appUserService.saveCustomer(appUser));
	}

	@PostMapping("/addNewProducts")
	public ResponseEntity<Products> addNewProducts(@RequestBody Products products) {
		return ResponseEntity.ok(appUserService.saveProduct(products));
	}

	@PostMapping("/addNewOrders")
	public ResponseEntity<Orders> addNewOrders(@RequestBody Orders orders) {
		return ResponseEntity.ok(appUserService.saveOrder(orders));
	}

	/**
	 * 
	 * HATEOS Hyperlinks Implementations
	 * 
	 * 
	 * @param appUser
	 * @return
	 */
	private Resource<AppUser> buildCustomerResources(AppUser appUser) {
		Resource<AppUser> resource = new Resource<>(appUser);
		// Link to Customers Entity
		resource.add(linkTo(methodOn(AppUserController.class).getUserById(appUser.getAppUserId())).withSelfRel());
		// Link to Parents Entity
		resource.add(linkTo(methodOn(AppUserController.class).findParent(appUser.getAppUserId())).withRel("Parents"));
		// Link to Orders Entity
		resource.add(linkTo(methodOn(AppUserController.class).findOrders(appUser.getAppUserId())).withRel("Orders"));
		// Link to Products Entity
		resource.add(
				linkTo(methodOn(AppUserController.class).findProducts(appUser.getAppUserId())).withRel("Products"));
		return resource;

	}
}
