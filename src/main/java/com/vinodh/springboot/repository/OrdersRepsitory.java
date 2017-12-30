package com.vinodh.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinodh.springboot.entity.Orders;

@Repository
public interface OrdersRepsitory extends JpaRepository<Orders, Long> {

}
