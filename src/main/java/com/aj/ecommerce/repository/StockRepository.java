package com.aj.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aj.ecommerce.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

}
