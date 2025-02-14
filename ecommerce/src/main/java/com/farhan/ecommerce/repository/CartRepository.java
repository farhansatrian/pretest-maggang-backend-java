package com.farhan.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.ecommerce.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, String> {

    Optional<Cart> findByCustomerIdAndProductId(String username, String productId);

    List<Cart> findByCustomerId(String username);

}
