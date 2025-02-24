package com.aj.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String createdBy;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String updatedBy;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public Integer getCategoryId() {
        return category != null ? category.getId() : null;  
    }

    public void setCategoryId(Integer categoryId) {
        if (category == null) { 
            category = new Category();
        }
        category.setId(categoryId);  
    }


    public Brand(Integer id, String name, String createdBy, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, String updatedBy, Category category) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
        this.updatedBy = updatedBy;
        this.category = category;
    }

    public Brand() {
    }
    
    @PrePersist
    public void onCreate() {
        this.createdDateTime = LocalDateTime.now();
        this.updatedDateTime = LocalDateTime.now();  
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedDateTime = LocalDateTime.now();  
    }
    
}




