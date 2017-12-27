package com.vinodh.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vinodh.springboot.entity.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long>, PagingAndSortingRepository<AppUser, Long> {

	@Query("SELECT users FROM AppUser users WHERE LOWER(users.userName) LIKE LOWER(:appuserName)")
	List<AppUser> getUserByName(@Param("appuserName") String appuserName);
}