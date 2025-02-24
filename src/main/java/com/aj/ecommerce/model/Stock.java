package com.aj.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
public class Stock {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;

	 @OneToOne
	 @JoinColumn(name = "product_id", nullable = false)
	 private Product product;

	 private Integer quantity;
	 private Double price;
	 
	 private LocalDateTime inDate;
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDateTime getInDate() {
		return inDate;
	}
	public void setInDate(LocalDateTime inDate) {
		this.inDate = inDate;
	}
	
	
	public Stock(Integer id, Product product, Integer quantity, Double price, LocalDateTime inDate) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.inDate = inDate;
	}
	
	public Stock() {
		super();
	}
	
}
