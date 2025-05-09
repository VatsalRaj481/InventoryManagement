package com.inventory.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.inventory.entity.Order;
import com.inventory.exception.OrderNotFoundException;
import com.inventory.repository.OrderRepository;

@Service
public class OrderService {
	
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order){
        order.setOrderDate(LocalDate.now());
        order.setStatus("Pending");
        Order savedOrder = orderRepository.save(order);
        System.out.println("Order created successfully: " + savedOrder);
        return savedOrder;
    }

    public List<Order> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        System.out.println("Fetched all orders successfully.");
        return orders;
    }

    public Order getOrderById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order with ID " + id + " not found."));
        System.out.println("Fetched order successfully: " + order);
        return order;
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
        System.out.println("Order deleted successfully with ID: " + id);
    }

    public Order updateStatus(Long id, String status){
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        System.out.println("Order status updated successfully: " + updatedOrder);
        return updatedOrder;
    }
}
