package com.aj.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aj.ecommerce.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{

}
