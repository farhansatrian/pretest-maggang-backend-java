package com.farhan.ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farhan.ecommerce.entity.Customer;
import com.farhan.ecommerce.service.CustomerService;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer findById(@PathVariable("id") String id) {
        return customerService.findById(id);
    }

    @PostMapping("/customers")
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @PutMapping("/customers")
    public Customer edit(@RequestBody Customer customer) {
        return customerService.edit(customer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteById(@PathVariable("id") String id) {
        customerService.deleteById(id);
    }

}
