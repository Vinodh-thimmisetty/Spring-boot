package com.vinodh.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vinodh.springboot.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	@Query("SELECT admin FROM Admin admin WHERE LOWER(admin.adminUserName) = LOWER(:adminUserName)")
	Admin getAdminByName(@Param("adminUserName") String adminUserName);
}
