package com.farhan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.ecommerce.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,String>{
    
}
