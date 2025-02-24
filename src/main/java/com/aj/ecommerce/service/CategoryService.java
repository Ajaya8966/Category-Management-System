package com.aj.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aj.ecommerce.model.Category;
import com.aj.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Category createCategory(Category category) {
        category.setCreatedDateTime(LocalDateTime.now());
        category.setUpdatedDateTime(LocalDateTime.now());
        return categoryRepository.save(category);
    }
 
    public Category updateCategory(int id, Category categoryDetails) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDetails.getName());
        category.setUpdatedBy(categoryDetails.getUpdatedBy());
        category.setUpdatedDateTime(LocalDateTime.now());
        return categoryRepository.save(category);
    }
    
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return categoryRepository.findById(id).isPresent();  
    }

   
    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
    
}
