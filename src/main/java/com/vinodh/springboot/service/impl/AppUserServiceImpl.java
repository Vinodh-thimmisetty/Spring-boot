package com.vinodh.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.repository.AppUserRepository;
import com.vinodh.springboot.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	AppUserRepository appUserRepository;

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
	public List<AppUser> getUserByName(String appuserName) {
		return appUserRepository.getUserByName("%" + appuserName + "%");
	}

	@Override
	public Page<AppUser> getUsersWIthPagination(PageRequest pageRequest) {
		return appUserRepository.findAll(pageRequest);
	}

}
