package com.farhan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.farhan.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
