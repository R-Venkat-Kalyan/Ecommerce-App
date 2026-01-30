package com.practice.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.ecom.dto.ProductRequest;
import com.practice.ecom.dto.ProductResponse;
import com.practice.ecom.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
		return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest), HttpStatus.CREATED);
	}

}
