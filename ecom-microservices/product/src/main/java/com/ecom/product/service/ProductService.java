package com.ecom.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.ecom.product.dto.ProductRequest;
import com.ecom.product.dto.ProductResponse;
import com.ecom.product.entity.Product;
import com.ecom.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

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

	public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
		return productRepository.findById(id).map(existingProduct -> {
			mapProductFromRequest(existingProduct, productRequest);
			Product savedProduct = productRepository.save(existingProduct);
			return mapToProductResponse(savedProduct);
		});
	}

	public List<ProductResponse> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findByActiveTrue().stream().map(this::mapToProductResponse)
				.collect(Collectors.toList());
	}

	public boolean deleteProduct(Long id) {
		// return productRepository.existsById(id);
		// return productRepository.findById(id).isPresent();
		return productRepository.findById(id).map(product -> {
			product.setActive(false);
			productRepository.save(product);
			return true;
		}).orElse(false);
	}

	public List<ProductResponse> searchProducts(String keyword) {
		
		return productRepository.searchProducts(keyword).stream()
				.map(this::mapToProductResponse)
				.collect(Collectors.toList());
		
	}

}
