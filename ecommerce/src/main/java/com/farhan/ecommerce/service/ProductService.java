package com.farhan.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.farhan.ecommerce.entity.Product;
import com.farhan.ecommerce.exception.BadRequestException;
import com.farhan.ecommerce.exception.ResourceNotFoundException;
import com.farhan.ecommerce.repository.CatalogRepository;
import com.farhan.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private CatalogRepository catalogRepository;

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
        if (!StringUtils.hasText(product.getName())) {
            throw new BadRequestException("Product name cannot be empty");
        }

        if (product.getCatalog() == null) {
            throw new BadRequestException("Catalog cannot be empty");
        }

        if (!StringUtils.hasText(product.getCatalog().getId())) {
            throw new BadRequestException("Catalog Id cannot be empty");
        }

        catalogRepository.findById(product.getCatalog().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Catalog Id " + product.getCatalog().getId() + " cannot found in database"));

        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    public Product edit(Product product) {
        if (!StringUtils.hasText(product.getId())) {
            throw new BadRequestException("Product ID must be filled");
        }

        if (!StringUtils.hasText(product.getName())) {
            throw new BadRequestException("Product name cannot be empty");
        }

        if (product.getCatalog() == null) {
            throw new BadRequestException("Catalog cannot be empty");
        }

        if (!StringUtils.hasText(product.getCatalog().getId())) {
            throw new BadRequestException("Catalog Id cannot be empty");
        }

        catalogRepository.findById(product.getCatalog().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Kategori ID " + product.getCatalog().getId() + " cannot found in database"));

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
