package com.vinodh.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinodh.springboot.entity.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

}
