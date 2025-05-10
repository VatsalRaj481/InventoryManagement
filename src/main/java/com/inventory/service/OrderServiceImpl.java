
package com.inventory.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.entity.Order;
import com.inventory.exception.OrderNotFoundException; // Assuming you have this or will create it
import com.inventory.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        order.setOrderDate(LocalDate.now());
        order.setStatus("Pending");
        Order savedOrder = orderRepository.save(order);
        System.out.println("Order created successfully: " + savedOrder);
        return savedOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        System.out.println("Fetched all orders successfully.");
        return orders;
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                                     .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found."));
        System.out.println("Fetched order successfully: " + order);
        return order;
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with ID " + id + " not found, cannot delete.");
        }
        orderRepository.deleteById(id);
        System.out.println("Order deleted successfully with ID: " + id);
    }

    @Override
    public Order updateStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                                     .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found, cannot update status."));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        System.out.println("Order status updated successfully: " + updatedOrder);
        return updatedOrder;
    }
}