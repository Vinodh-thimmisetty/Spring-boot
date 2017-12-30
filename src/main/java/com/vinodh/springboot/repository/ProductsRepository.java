package com.vinodh.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinodh.springboot.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

	 
}
