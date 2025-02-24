package com.aj.ecommerce.controller;

import com.aj.ecommerce.model.Brand;
import com.aj.ecommerce.model.Category;
import com.aj.ecommerce.service.BrandService;
import com.aj.ecommerce.service.CategoryService;
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

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody Brand brand) {
        try {
            // Fetch the Category by its ID
            Category category = categoryService.getCategoryById(brand.getCategoryId());

            // Check if the category exists or not
            if (category == null) {
                return new ResponseEntity<>("Category not found", HttpStatus.BAD_REQUEST);
            }

            
            brand.setCategory(category);

            // Save
            Brand savedBrand = brandService.createBrand(brand);
            return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public Brand updateBrand(@PathVariable int id, @RequestBody Brand brand) {
    	return brandService.updateBrand(id, brand);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable int id){
    	boolean exists = brandService.existsById(id);
    	if (!exists) {
    		return new ResponseEntity<>("Brand with id " + id + " does not exist", HttpStatus.NOT_FOUND);
    	}
    	brandService.deleteBrand(id);
        String successMessage = "Brand with id " + id + " has been successfully deleted";
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
    
}
