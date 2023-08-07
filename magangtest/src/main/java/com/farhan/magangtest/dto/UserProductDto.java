package com.farhan.magangtest.dto;

import lombok.Data;

public class UserProductDto {

    @Data
    public static class save {
        private int user_id;
        private int product_id;
        private int quantity;
    }

    @Data
    public static class update {
        private int user_id;
        private int product_id;
        private int quantity;
    }
}
