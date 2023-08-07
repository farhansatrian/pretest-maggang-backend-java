package com.farhan.magangtest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private int category_id;
    private int stock;
    private String description;
    private int price;
    private String image;

    private String category_name;

}
