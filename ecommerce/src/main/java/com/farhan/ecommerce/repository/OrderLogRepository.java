package com.farhan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.ecommerce.entity.OrderLog;

public interface OrderLogRepository extends JpaRepository<OrderLog, String> {

}
