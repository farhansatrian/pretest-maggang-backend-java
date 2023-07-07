package com.farhan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.ecommerce.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, String>{
    
}