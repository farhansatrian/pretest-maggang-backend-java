package com.farhan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.ecommerce.entity.OrderBuy;

public interface OrderItemRepository extends JpaRepository<OrderBuy, String>{
    
}