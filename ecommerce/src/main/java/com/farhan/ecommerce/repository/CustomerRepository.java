package com.farhan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsByEmail(String email);

}
