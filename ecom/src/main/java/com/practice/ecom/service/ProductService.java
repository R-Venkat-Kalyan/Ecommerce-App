package com.practice.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.ecom.dto.ProductRequest;
import com.practice.ecom.dto.ProductResponse;
import com.practice.ecom.entity.Product;
import com.practice.ecom.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public ProductResponse createProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		Product product = new Product();
		mapProductFromRequest(product, productRequest);
		Product savedProduct = productRepository.save(product);
		return mapToProductResponse(savedProduct);
	}
	

	private void mapProductFromRequest(Product product, ProductRequest productRequest) {
		// TODO Auto-generated method stub
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setPrice(productRequest.getPrice());
		product.setStockQuantity(productRequest.getStockQuantity());
		product.setCategory(productRequest.getCategory());
		product.setImageUrl(productRequest.getImageUrl());
	}
	
	private ProductResponse mapToProductResponse(Product savedProduct) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(savedProduct.getId());
		productResponse.setName(savedProduct.getName());
		productResponse.setDescription(savedProduct.getDescription());
		productResponse.setPrice(savedProduct.getPrice());
		productResponse.setCategory(savedProduct.getCategory());
		productResponse.setImageUrl(savedProduct.getImageUrl());
		productResponse.setStockQuantity(savedProduct.getStockQuantity());
		productResponse.setActive(savedProduct.getActive());
		return productResponse;
	}
	
	
	
	

}
