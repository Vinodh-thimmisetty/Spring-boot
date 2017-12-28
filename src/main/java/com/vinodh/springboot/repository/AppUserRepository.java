package com.vinodh.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vinodh.springboot.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	@Query("SELECT users FROM AppUser users WHERE LOWER(users.userName) LIKE LOWER(:appuserName)")
	List<AppUser> getUserByName(@Param("appuserName") String appuserName);
}
