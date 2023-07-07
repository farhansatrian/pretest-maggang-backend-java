package com.farhan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.ecommerce.entity.Cart;

public interface CustomerRepository extends JpaRepository<Cart, String>{
    
}
