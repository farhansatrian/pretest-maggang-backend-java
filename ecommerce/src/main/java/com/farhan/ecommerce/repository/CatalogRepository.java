package com.farhan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.ecommerce.entity.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, String> {
    
}
