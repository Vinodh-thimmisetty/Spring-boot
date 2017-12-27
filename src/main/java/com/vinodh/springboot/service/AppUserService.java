package com.vinodh.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.vinodh.springboot.entity.AppUser;

public interface AppUserService {

	List<AppUser> getUsers();

	AppUser getUserById(Long appuserId);

	List<AppUser> getUserByName(String appuserName);

	Page<AppUser> getUsersWIthPagination(PageRequest pageRequest);

}
