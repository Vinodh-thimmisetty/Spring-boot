package com.vinodh.springboot.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vinodh.springboot.domain.CustomerDTO;
import com.vinodh.springboot.domain.OrdersDTO;
import com.vinodh.springboot.domain.ParentsDTO;
import com.vinodh.springboot.domain.ProductsDTO;
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
	public ResponseEntity<List<CustomerDTO>> getUsers() {
		return ResponseEntity
				.ok(appUserService.getUsers().stream().map(x -> new CustomerDTO(x)).collect(Collectors.toList()));
	}

	@GetMapping(path = "/findAllUsers/hateos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Resource<CustomerDTO>>> getUsersHateaos() {
		log.info("Buildng Resource Links");
		List<AppUser> appUsers = appUserService.getUsers();
		List<Resource<CustomerDTO>> resources = new ArrayList<>();
		for (AppUser appUser : appUsers) {
			resources.add(buildCustomerResources(new CustomerDTO(appUser)));
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
	public ResponseEntity<Resource<CustomerDTO>> getUserById(@PathVariable("appuserId") Long appuserId) {
		AppUser appUser = appUserService.getUserById(appuserId);
		if (Objects.isNull(appUser)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(buildCustomerResources(new CustomerDTO(appUser)));
	}

	@GetMapping("/findUser/hateos/{appuserId:[0-9]+}")
	public ResponseEntity<AppUser> getUserByIdHateaos(@PathVariable("appuserId") Long appuserId) {
		return ResponseEntity.ok(appUserService.getUserById(appuserId));
	}

	@GetMapping("/findUser/{appuserName:[a-zA-Z]+}")
	public ResponseEntity<CustomerDTO> getUserByName(@PathVariable("appuserName") String appuserName) {
		return ResponseEntity.ok(new CustomerDTO(appUserService.getUserByName(appuserName)));
	}

	@GetMapping("/findUserbyName/{appuserName}")
	public ResponseEntity<CustomerDTO> getUserByName1(@PathVariable("appuserName") String appuserName) {
		return ResponseEntity.ok(new CustomerDTO(appUserService.getUserByName(appuserName)));
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
	public ResponseEntity<CustomerDTO> addNewCustomer(@RequestBody CustomerDTO customerInfo) {
		AppUser appUser = new AppUser(customerInfo);

		/* Save Parents */
		if (Objects.nonNull(customerInfo.getParents())) {
			Parents parents = new Parents(customerInfo.getParents());
			parents.setCustomer(appUser);
			appUser.setParents(parents);
		}
		/* Save Products */
		if (CollectionUtils.isNotEmpty(customerInfo.getProducts())) {
			List<Products> productsList = customerInfo.getProducts().stream()
					.map(eachProduct -> new Products(eachProduct)).collect(Collectors.toList());
			productsList.stream().forEach(x -> x.setCustomer(appUser));
			appUser.setProducts(productsList);
		}
		/* Save Orders */
		if (CollectionUtils.isNotEmpty(customerInfo.getOrders())) {
			List<Orders> ordersList = customerInfo.getOrders().stream().map(eachOrder -> new Orders(eachOrder))
					.collect(Collectors.toList());
			ordersList.stream().forEach(x -> x.setCustomer(appUser));
			appUser.setOrders(ordersList);
		}
		return ResponseEntity.ok(new CustomerDTO(appUserService.saveCustomer(appUser)));
	}

	@PostMapping("/addNewParents")
	public ResponseEntity<CustomerDTO> addNewParents(@RequestBody ParentsDTO parentsDTO) {
		Parents parents = new Parents(parentsDTO);
		AppUser appUser = appUserService.getUserById(parents.getAppUserId());
		parents.setCustomer(appUser);
		appUser.setParents(parents);
		return ResponseEntity.ok(new CustomerDTO(appUserService.saveCustomer(appUser)));
	}

	@PostMapping("/addNewProducts/{customerId}")
	public ModelAndView addNewProducts(@RequestBody List<ProductsDTO> productDTOs,
			@PathVariable("customerId") Long customerId) {
		List<Products> productsList = productDTOs.stream().map(eachProduct -> new Products(eachProduct))
				.collect(Collectors.toList());
		AppUser appUser = appUserService.getUserById(customerId);
		productsList.stream().forEach(x -> x.setCustomer(appUser));
		appUser.setProducts(productsList);
		appUserService.saveCustomer(appUser);
		return new ModelAndView("redirect:/user/findUser/" + customerId);
	}

	@PostMapping("/addNewOrders/{customerId}")
	public ModelAndView addNewOrders(@RequestBody List<OrdersDTO> orderDTOs,
			@PathVariable("customerId") Long customerId) {

		List<Orders> ordersList = orderDTOs.stream().map(eachOrder -> new Orders(eachOrder))
				.collect(Collectors.toList());
		AppUser appUser = appUserService.getUserById(customerId);
		ordersList.stream().forEach(x -> x.setCustomer(appUser));
		appUser.setOrders(ordersList);
		appUserService.saveCustomer(appUser);

		return new ModelAndView("redirect:/user/findUser/" + customerId);
	}

	/**
	 * 
	 * HATEOS Hyperlinks Implementations
	 * 
	 * 
	 * @param appUser
	 * @return
	 */
	private Resource<CustomerDTO> buildCustomerResources(CustomerDTO customer) {
		Resource<CustomerDTO> resource = new Resource<>(customer);
		// Link to Customers Entity
		resource.add(linkTo(methodOn(AppUserController.class).getUserById(customer.getCustomerId())).withSelfRel());
		// Link to Parents Entity
		resource.add(linkTo(methodOn(AppUserController.class).findParent(customer.getCustomerId())).withRel("Parents"));
		// Link to Orders Entity
		resource.add(linkTo(methodOn(AppUserController.class).findOrders(customer.getCustomerId())).withRel("Orders"));
		// Link to Products Entity
		resource.add(
				linkTo(methodOn(AppUserController.class).findProducts(customer.getCustomerId())).withRel("Products"));
		return resource;

	}
}
