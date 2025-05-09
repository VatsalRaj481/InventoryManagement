package com.inventory.controller;

import com.inventory.entity.Stock;
import com.inventory.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public Stock addStock(@RequestBody Stock stock){
        return stockService.addStock(stock);
    }

    @PutMapping("/{productId}")
    public Stock updateStock(@PathVariable Long productId, @RequestBody Stock stock){
        return stockService.updateStock(productId, stock);
    }

    @DeleteMapping("/{productId}")
    public void deleteStock(@PathVariable Long productId){
        stockService.deleteStock(productId);
    }

    @GetMapping
    public List<Stock> getAllStock(){
        return stockService.getAllStock();
    }

    @GetMapping("/{productId}")
    public Stock getStockByProductId(@PathVariable Long productId){
        return stockService.getStockByProductId(productId);
    }
}
