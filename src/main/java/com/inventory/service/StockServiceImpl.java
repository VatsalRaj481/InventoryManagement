package com.inventory.service;

import com.inventory.entity.Stock;
import com.inventory.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock addStock(Stock stock) {
        Stock saved = stockRepository.save(stock);
        saved.setLowStock(saved.getQuantity() <= saved.getReorderLevel());
        System.out.println("Stock added successfully: " + saved);
        return saved;
    }

    @Override
    public Stock updateStock(Long productId, Stock stock) {
        Stock existing = stockRepository.findById(productId).orElseThrow(() -> new RuntimeException("Stock not found"));

        existing.setQuantity(stock.getQuantity());
        existing.setReorderLevel(stock.getReorderLevel());
        existing.setLowStock(stock.getQuantity() <= stock.getReorderLevel());

        Stock updated = stockRepository.save(existing);
        System.out.println("Stock updated successfully: " + updated);
        return updated;
    }

    @Override
    public void deleteStock(Long productId) {
        stockRepository.deleteById(productId);
        System.out.println("Stock deleted successfully for product ID: " + productId);
    }

    @Override
    public List<Stock> getAllStock() {
        List<Stock> stocks = stockRepository.findAll();
        for (Stock stock : stocks) {
            stock.setLowStock(stock.getQuantity() <= stock.getReorderLevel());
        }
        System.out.println("Fetched all stock records successfully.");
        return stocks;
    }

    @Override
    public Stock getStockByProductId(Long productId) {
        Stock stock = stockRepository.findById(productId).orElse(null);
        if (stock != null) {
            stock.setLowStock(stock.getQuantity() <= stock.getReorderLevel());
            System.out.println("Fetched stock successfully: " + stock);
        } else {
            System.out.println("Stock not found for product ID: " + productId);
        }
        return stock;
    }
}
