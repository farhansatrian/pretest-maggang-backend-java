package com.farhan.ecommerce.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Cart implements Serializable {
    @Id
    private String id;
    @JoinColumn
    @ManyToOne
    private Customer customer;
    private Double Quantity;
    private BigDecimal price;
    private BigDecimal amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creDate;
}
