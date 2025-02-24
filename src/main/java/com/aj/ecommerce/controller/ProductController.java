package com.aj.ecommerce.controller;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aj.ecommerce.model.Brand;
import com.aj.ecommerce.model.Category;
import com.aj.ecommerce.model.Product;
import com.aj.ecommerce.service.BrandService;
import com.aj.ecommerce.service.CategoryService;
import com.aj.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            // Get category and brand ID from the request (assuming they are passed in JSON)
            int categoryId = product.getCategory().getId(); // Extract category ID
            int brandId = product.getBrand().getId(); // Extract brand ID

            // Fetching Category and Brand from database
            Category category = categoryService.getCategoryById(categoryId);
            Brand brand = brandService.getBrandById(brandId);

            // Check if Category exists
            if (category == null) {
                return new ResponseEntity<>("Category not found", HttpStatus.BAD_REQUEST);
            }

            // Check if Brand exists
            if (brand == null) {
                return new ResponseEntity<>("Brand not found", HttpStatus.BAD_REQUEST);
            }

            // Assign fetched category and brand to the product
            product.setCategory(category);
            product.setBrand(brand);

            // Save 
            Product savedProduct = productService.createProduct(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    @PutMapping("/{id}")
//    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
//    	return productService.updateProduct(id, product);
//    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
    
    // Deleting the Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
    	boolean exists = productService.existsById(id);
    	if(!exists) {
    		return new ResponseEntity<>("Product with id " + id + " does not exists", HttpStatus.NOT_FOUND);
    	}
    	productService.deleteProduct(id);
        String successMessage = "Product with id " + id + " has been successfully deleted";
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
    
    
}
