package com.farhan.ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farhan.ecommerce.entity.Product;
import com.farhan.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @PostMapping("/products")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/products")
    public Product edit(@RequestBody Product product) {
        return productService.edit(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteById(@PathVariable("id") String id) {
        productService.deleteById(id);
    }

}
