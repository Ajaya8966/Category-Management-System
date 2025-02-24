package com.aj.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
//import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String createdBy;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String updatedBy;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public LocalDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
	public Category() {
		super();
	}
	
	public Category(int id, String name, String createdBy, LocalDateTime createdDateTime, LocalDateTime updatedDateTime,
			String updatedBy) {
		super();
		this.id = id;
		this.name = name;
		this.createdBy = createdBy;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;
		this.updatedBy = updatedBy;
	}
	
	 @OneToMany(mappedBy = "category")
	 private List<Brand> brands;
    
	 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Product> products;

}
