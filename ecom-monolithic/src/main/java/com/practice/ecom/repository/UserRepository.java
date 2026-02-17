package com.practice.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.ecom.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
