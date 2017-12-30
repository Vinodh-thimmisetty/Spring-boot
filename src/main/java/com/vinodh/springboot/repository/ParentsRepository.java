package com.vinodh.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinodh.springboot.entity.Parents;

@Repository
public interface ParentsRepository extends JpaRepository<Parents, Long> {

}