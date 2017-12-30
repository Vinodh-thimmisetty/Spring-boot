package com.vinodh.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.entity.Orders;
import com.vinodh.springboot.entity.Parents;
import com.vinodh.springboot.entity.Products;

public interface AppUserService {

	List<AppUser> getUsers();

	AppUser getUserById(Long appuserId);

	List<AppUser> getUserByName(String appuserName);

	Page<AppUser> getUsersWIthPagination(PageRequest pageRequest);

	AppUser saveCustomer(AppUser appUser);

	Products saveProduct(Products products);

	Orders saveOrder(Orders orders);

	Parents saveParents(Parents parents);

}
