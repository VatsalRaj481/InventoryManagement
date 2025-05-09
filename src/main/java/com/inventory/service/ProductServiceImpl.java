package com.inventory.service; 

import com.inventory.entity.Product;
import com.inventory.exception.*;
import com.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new DuplicateProductException("Product with name " + product.getName() + " already exists.");
        }
        Product savedProduct = productRepository.save(product);
        System.out.println("Product created successfully: " + savedProduct);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        // product.setStockLevel(updatedProduct.getStockLevel());
        Product updated = productRepository.save(product);
        System.out.println("Product updated successfully: " + updated);
        return updated;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        System.out.println("Product deleted successfully with ID: " + id);
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product with ID " + id + " not found."));
        System.out.println("Fetched product successfully: " + product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        System.out.println("Fetched all products successfully.");
        return products;
    }
}
