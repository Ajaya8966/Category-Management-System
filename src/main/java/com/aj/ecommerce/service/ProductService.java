package com.aj.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aj.ecommerce.model.Product;
import com.aj.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	// Creating Products
	public Product createProduct(Product product) {
		product.setCreatedDateTime(LocalDateTime.now());
		product.setUpdatedDateTime(LocalDateTime.now());
		return productRepository.save(product);
	}

	// Updating Products
	public Product updateProduct(int id, Product productDetails) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		product.setProductName(productDetails.getProductName());
		product.setUpdatedBy(productDetails.getUpdatedBy());
		product.setUpdatedDateTime(LocalDateTime.now());
		
		return productRepository.save(product);
	}
	
	// Delete Products
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return productRepository.findById(id).isPresent();
	}
	
	public Product getProductById(Integer productId) {
		return productRepository.findById(productId).orElse(null);
	}
}
