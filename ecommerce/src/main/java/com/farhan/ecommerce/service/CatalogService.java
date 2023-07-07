package com.farhan.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farhan.ecommerce.entity.Catalog;
import com.farhan.ecommerce.exception.ResourceNotFoundException;
import com.farhan.ecommerce.repository.CatalogRepository;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    public Catalog findById(String id) {
        return catalogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog with id " + id + " Not Found"));
    }

    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    public Catalog create(Catalog catalog) {
        catalog.setId(UUID.randomUUID().toString());
        return catalogRepository.save(catalog);
    }

    public Catalog edit(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    public void deleteById(String id){
        catalogRepository.deleteById(id);
    }
}
