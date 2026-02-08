package com.practice.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.ecom.dto.CartItemRequest;
import com.practice.ecom.entity.CartItem;
import com.practice.ecom.entity.Product;
import com.practice.ecom.entity.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	CartItem findByUserAndProduct(User user, Product product);

	void deleteByUserAndProduct(User user, Product product);

	List<CartItem> findByUser(User user);

	void deleteByUser(User user);

}
