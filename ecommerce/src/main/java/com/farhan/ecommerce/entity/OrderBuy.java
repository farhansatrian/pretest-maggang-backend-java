package com.farhan.ecommerce.entity;

import java.util.Date;

import com.model.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class OrderBuy {

    @Id
    private String id;
    private String number;
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn
    @ManyToOne
    private Customer customer;
    private String shippingAddress;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
}
