package com.aj.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aj.ecommerce.dto.CategoryDTO;
import com.aj.ecommerce.model.Category;
import com.aj.ecommerce.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
    	Category savedCategory = categoryService.createCategory(categoryDTO);
    	return ResponseEntity.ok(savedCategory);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        boolean exists = categoryService.existsById(id);
        if (!exists) {
            return new ResponseEntity<>("Category with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        categoryService.deleteCategory(id);
        String successMessage = "Category with id " + id + " has been successfully deleted";
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }


}
