package com.vinodh.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.entity.Orders;
import com.vinodh.springboot.entity.Parents;
import com.vinodh.springboot.entity.Products;
import com.vinodh.springboot.repository.AppUserRepository;
import com.vinodh.springboot.repository.OrdersRepsitory;
import com.vinodh.springboot.repository.ParentsRepository;
import com.vinodh.springboot.repository.ProductsRepository;
import com.vinodh.springboot.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	OrdersRepsitory ordersRepsitory;

	@Autowired
	ParentsRepository parentsRepository;

	@Override
	public List<AppUser> getUsers() {
		List<AppUser> appUsers = new ArrayList<>();
		appUserRepository.findAll().forEach(appUsers::add);
		return appUsers;
	}

	@Override
	public AppUser getUserById(Long appuserId) {
		return appUserRepository.findOne(appuserId);
	}

	@Override
	public List<AppUser> getUserByfirstName(String appuserName) {
		return appUserRepository.getUserByfirstName("%" + appuserName + "%");
	}

	@Override
	public Page<AppUser> getUsersWIthPagination(PageRequest pageRequest) {
		return appUserRepository.findAll(pageRequest);
	}

	@Override
	public AppUser saveCustomer(AppUser appUser) {
		return appUserRepository.save(appUser);
	}

	@Override
	public Products saveProduct(Products products) {
		return productsRepository.save(products);
	}

	@Override
	public Orders saveOrder(Orders orders) {
		return ordersRepsitory.save(orders);
	}

	@Override
	public Parents saveParents(Parents parents) {
		return parentsRepository.save(parents);
	}

	@Override
	public AppUser getUserByName(String appuserName) {
		return appUserRepository.getUserByName(appuserName);
	}
}