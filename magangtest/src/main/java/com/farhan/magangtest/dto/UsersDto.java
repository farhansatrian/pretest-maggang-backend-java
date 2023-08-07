package com.farhan.magangtest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UsersDto {

    @Data
    public static class save {
        @NotEmpty(message = "Nama Tidak Boleh Kosong")
        @NotNull(message = "Nama Tidak Boleh Null")

        private String name;

        @Email
        private String email;

        @NotEmpty(message = "Nomor Tidak Boleh Kosong")
        @NotNull(message = "Nomor Tidak Boleh Null")
        private String phone;

        private String address;
    }

    @Data
    public static class update {
        private int id;
        @NotEmpty(message = "Nama Tidak Boleh Kosong")
        @NotNull(message = "Nama Tidak Boleh Null")

        private String name;

        @Email
        private String email;

        @NotEmpty(message = "Nomor Tidak Boleh Kosong")
        @NotNull(message = "Nomor Tidak Boleh Null")
        @Size(max = 13,min = 11)
        private String phone;

        private String address;

    }
}
