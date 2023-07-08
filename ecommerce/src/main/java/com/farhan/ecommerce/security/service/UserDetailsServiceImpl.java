package com.farhan.ecommerce.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.farhan.ecommerce.entity.Customer;
import com.farhan.ecommerce.repository.CustomerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " Not Found"));

        return UserDetailsImpl.build(customer);
    }
}
