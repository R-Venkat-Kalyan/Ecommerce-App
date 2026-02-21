package com.ecom.product.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom.product.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByActiveTrue();
	
	@Query("Select p from Product p where p.active = true and p.stockQuantity > 0 and lower(p.name) like lower(concat('%', :keyword, '%'))")
	List<Product> searchProducts(@Param("keyword") String keyword);

}
