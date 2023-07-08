package com.farhan.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farhan.ecommerce.entity.Cart;
import com.farhan.ecommerce.entity.Customer;
import com.farhan.ecommerce.entity.Product;
import com.farhan.ecommerce.exception.BadRequestException;
import com.farhan.ecommerce.repository.CartRepository;
import com.farhan.ecommerce.repository.ProductRepository;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public Cart addCart(String username, String productId, Double quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException("Product ID " + productId + " can't found"));

        Optional<Cart> optional = cartRepository.findByCustomerIdAndProductId(username, productId);
        Cart cart;
        if (optional.isPresent()) {
            cart = optional.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            cart.setAmount(new BigDecimal(cart.getPrice().doubleValue() * cart.getQuantity()));
            cartRepository.save(cart);
        } else {
            cart = new Cart();
            cart.setId(UUID.randomUUID().toString());
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cart.setPrice(product.getPrice());
            cart.setAmount(new BigDecimal(cart.getPrice().doubleValue() * cart.getQuantity()));
            cart.setCustomer(new Customer(username));
            cartRepository.save(cart);
        }

        return cart;
    }

    @Transactional
    public Cart updateQuantity(String username, String productId, Double quantity) {
        Cart cart = cartRepository.findByCustomerIdAndProductId(username, productId)
                .orElseThrow(() -> new BadRequestException("Product Id " + productId + " cant find in your cart"));
        cart.setQuantity(quantity);
        cart.setAmount(new BigDecimal(cart.getPrice().doubleValue() * cart.getQuantity()));
        cartRepository.save(cart);
        return cart;
    }

    @Transactional
    public void delete(String username, String productId) {
        Cart cart = cartRepository.findByCustomerIdAndProductId(username, productId)
                .orElseThrow(() -> new BadRequestException("Product Id " + productId + " cant find in your cart"));

        cartRepository.delete(cart);
    }

    public List<Cart> findByCustomerId(String username) {
        return cartRepository.findByCustomerId(username);
    }
}
