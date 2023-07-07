package com.farhan.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farhan.ecommerce.entity.Product;
import com.farhan.ecommerce.exception.ResourceNotFoundException;
import com.farhan.ecommerce.repository.CatalogRepository;
import com.farhan.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " Not Found"));
    }

    public Product create(Product product) {
        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    public Product edit(Product product) {
        return productRepository.save(product);
    }

    public Product changeImage(String id, String image) {
        Product product = findById(id);
        product.setImage(image);
        return productRepository.save(product);
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
