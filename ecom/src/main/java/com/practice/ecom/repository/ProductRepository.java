package com.practice.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.ecom.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
