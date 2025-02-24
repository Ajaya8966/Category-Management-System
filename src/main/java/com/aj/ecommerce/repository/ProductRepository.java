package com.aj.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aj.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
