package com.inventory.entity;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long customerId;
    private Long productId;
    private int quantity;
    private LocalDate orderDate;
    private String status;

    @ManyToMany
    @JoinTable(
    		name="order_product",
    		joinColumns = @JoinColumn(name="order_id"),
    		inverseJoinColumns = @JoinColumn(name="product_id")
    		)
    private List<Product> products;
     public Long getOrderId() {
         return orderId;
     }
     public void setOrderId(Long orderId) {
         this.orderId = orderId;
     }
     public Long getCustomerId() {
         return customerId;
     }
     public void setCustomerId(Long customerId) {
         this.customerId = customerId;
     }
     public Long getProductId() {
         return productId;
     }
     public void setProductId(Long productId) {
         this.productId = productId;
     }
     public int getQuantity() {
         return quantity;
     }
     public void setQuantity(int quantity) {
         this.quantity = quantity;
     }
     public LocalDate getOrderDate() {
         return orderDate;
     }
     public void setOrderDate(LocalDate orderDate) {
         this.orderDate = orderDate;
     }
     public String getStatus() {
         return status;
     }
     public void setStatus(String status) {
         this.status = status;
     }
    



}