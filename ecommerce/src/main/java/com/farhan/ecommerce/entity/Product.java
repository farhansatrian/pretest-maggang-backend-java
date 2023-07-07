package com.farhan.ecommerce.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

public class Product implements Serializable {
    @Id
    private String id;
    private String name;
    private String description;
    private String image;
    @JoinColumn
    @ManyToOne
    private Catalog catalog;
    private BigDecimal price;
    private Double stock;

}
