package com.aj.ecommerce.controller;

import com.aj.ecommerce.model.Stock;
import com.aj.ecommerce.model.Product;
import com.aj.ecommerce.service.StockService;
import com.aj.ecommerce.service.ProductService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @PostMapping
    public ResponseEntity<?> createStock(@RequestBody Stock stock) {
        try {
            // Fetch the Product by its ID
            Product product = productService.getProductById(stock.getProduct().getId());

            // Check if the product exists or not
            if (product == null) {
                return new ResponseEntity<>("Product not found", HttpStatus.BAD_REQUEST);
            }

            // Set the product in the Stock
            stock.setProduct(product);

            // Save 
            Stock savedStock = stockService.createStock(stock);
            return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStock(@PathVariable int id, @RequestBody Stock stock) {
        try {
            // checking that the stock exists or not before updating
            Stock existingStock = stockService.getStockById(id);
            if (existingStock == null) {
                return new ResponseEntity<>("Stock not found", HttpStatus.NOT_FOUND);
            }

            // update the products and other details
            stock.setId(id);  // Ensure the ID is preserved
            Stock updatedStock = stockService.updateStock(id, stock);
            return new ResponseEntity<>(updatedStock, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable int id) {
        boolean exists = stockService.existsById(id);
        if (!exists) {
            return new ResponseEntity<>("Stock with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
        stockService.deleteStock(id);
        String successMessage = "Stock with id " + id + " has been successfully deleted";
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
}
