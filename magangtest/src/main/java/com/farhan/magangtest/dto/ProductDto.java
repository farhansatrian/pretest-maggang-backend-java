package com.farhan.magangtest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

public class ProductDto {

    @Data
    public static class save {
        @NotEmpty(message = "Nama Tidak Boleh Kosong")
        @NotNull(message = "Nama Tidak Boleh Null")
        private String name;

//        @NotNull(message = "Category Id Tidak Boleh Null")
//        @Size(min=1)
        private int category_id;

//        @NotNull(message = "Stock Tidak Boleh Null")
//        @Size(min = 1)
        private int stock;
        @NotEmpty(message = "Description Tidak Boleh Kosong")
        @NotNull(message = "Description Tidak Boleh Null")
        private String description;

//        @NotNull(message = "Price Tidak Boleh Null")
//        @Size(min = 1)
        private int price;
        private String image;
    }

    @Data
    public static class update {
//        @Size(min = 1)
        private int id;
        @NotEmpty(message = "Nama Tidak Boleh Kosong")
        @NotNull(message = "Nama Tidak Boleh Null")
        private String name;

        //        @NotNull(message = "Category Id Tidak Boleh Null")
//        @Size(min=1)
        private int category_id;

        //        @NotNull(message = "Stock Tidak Boleh Null")
//        @Size(min = 1)
        private int stock;
        @NotEmpty(message = "Description Tidak Boleh Kosong")
        @NotNull(message = "Description Tidak Boleh Null")
        private String description;

        //        @NotNull(message = "Price Tidak Boleh Null")
//        @Size(min = 1)
        private int price;
        private String image;
        private String category_name;
    }
}
