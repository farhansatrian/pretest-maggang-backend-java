package com.farhan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.ecommerce.entity.OrderBuy;

public interface OrderBuyRepository extends JpaRepository<OrderBuy, String>{
    
}
