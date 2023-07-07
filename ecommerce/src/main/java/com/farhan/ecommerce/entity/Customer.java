package com.farhan.ecommerce.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Customer implements Serializable {

    @Id
    private String id;
    private String password;
    private String email;
    private String name;
    private String address;
    private String phone;
    private String roles;
    private Boolean isActive;
 
}
