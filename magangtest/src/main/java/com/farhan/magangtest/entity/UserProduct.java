package com.farhan.magangtest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProduct {

    private int id;
    private int user_id;
    private int product_id;
    private int quantity;

    private String user_name;
    private String product_name;
}
