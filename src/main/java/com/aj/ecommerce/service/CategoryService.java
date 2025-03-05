package com.aj.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aj.ecommerce.dto.CategoryDTO;
import com.aj.ecommerce.model.Category;
import com.aj.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    //Create Category
    public boolean categoryExists(String name) {
        return categoryRepository.existsByName(name);
    }
    
    public Category createCategory(CategoryDTO categoryDTO) {
       if (categoryRepository.existsByName(categoryDTO.getName())) {
    	   throw new RuntimeException("Category name already exists");
       }
    	
       Category category = new Category();
       category.setName(categoryDTO.getName());
       category.setCreatedBy(categoryDTO.getCreatedBy());
       category.setUpdatedBy(categoryDTO.getUpdatedBy());
       return categoryRepository.save(category);
    }
 
    //update Category
 // Method to check if category name exists excluding current category
    public boolean existsByNameAndIdNot(String name, long i) {
        return categoryRepository.existsByNameAndIdNot(name, i);
    }

    public void updateCategory(Integer id, CategoryDTO categoryDTO) {
        // Get the category by ID
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Update the category's fields with DTO values
        category.setName(categoryDTO.getName());
        category.setUpdatedBy(categoryDTO.getUpdatedBy());
        category.setUpdatedDateTime(LocalDateTime.now());

        // Save the updated category
        categoryRepository.save(category);
    }

    
  //Delete Category
    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return categoryRepository.findById(id).isPresent();  
    }

   
    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

 // Method to delete a category by ID
    public void deleteById(Integer id) {
        // Check if the category exists before attempting to delete
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id); // Delete the category
        } else {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist.");
        }
    }


//	public boolean existsByName(String name) {
//		// TODO Auto-generated method stub
//		return false;
//	}
    
}
