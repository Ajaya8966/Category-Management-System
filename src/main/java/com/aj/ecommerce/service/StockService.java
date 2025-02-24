package com.aj.ecommerce.service;

import com.aj.ecommerce.model.Stock;
import com.aj.ecommerce.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    // Get all Stocks
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // Create Stock
    public Stock createStock(Stock stock) {
        stock.setInDate(LocalDateTime.now());  
        return stockRepository.save(stock);
    }

    // Update Stock
    public Stock updateStock(int id, Stock stockDetails) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.setQuantity(stockDetails.getQuantity());
        stock.setPrice(stockDetails.getPrice());
        return stockRepository.save(stock);
    }

    // Delete Stock
    public void deleteStock(int id) {
        stockRepository.deleteById(id);
    }

    // Check if Stock exists by ID
    public boolean existsById(int id) {
        return stockRepository.findById(id).isPresent();
    }

    // Get Stock by ID
    public Stock getStockById(Integer stockId) {
        return stockRepository.findById(stockId).orElse(null);
    }
}
