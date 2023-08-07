package com.farhan.magangtest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class CategoryDto {

    @Data
    public static class save{
        @NotEmpty
        @NotNull
        private String name;
    }

    @Data
    public static class update{
        private int id;
        @NotEmpty
        @NotNull
        private String name;
    }
}
