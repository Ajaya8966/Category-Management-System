package com.aj.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aj.ecommerce.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Long id);
}
