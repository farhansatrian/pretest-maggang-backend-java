package com.farhan.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.farhan.ecommerce.entity.Customer;
import com.farhan.ecommerce.exception.BadRequestException;
import com.farhan.ecommerce.exception.ResourceNotFoundException;
import com.farhan.ecommerce.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " Not Found"));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer create(Customer customer) {
        if (!StringUtils.hasText(customer.getId())) {
            throw new BadRequestException("Username must be filled");
        }

        if (customerRepository.existsById(customer.getId())) {
            throw new BadRequestException("Username " + customer.getId() + " already registered");
        }

        if (!StringUtils.hasText(customer.getEmail())) {
            throw new BadRequestException("Email must be filled");
        }

        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new BadRequestException("Email " + customer.getEmail() + " Already Registered");
        }

        customer.setIsActive(true);

        return customerRepository.save(customer);
    }

    public Customer edit(Customer customer) {
        if (!StringUtils.hasText(customer.getId())) {
            throw new BadRequestException("Username must be filled");
        }

        if (!StringUtils.hasText(customer.getEmail())) {
            throw new BadRequestException("Email must be filled");
        }

        return customerRepository.save(customer);
    }

    public void deleteById(String id) {
        customerRepository.deleteById(id);
    }
}
