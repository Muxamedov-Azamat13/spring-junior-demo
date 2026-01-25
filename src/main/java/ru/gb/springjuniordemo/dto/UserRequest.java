package ru.gb.springjuniordemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    @Min(0)
    private Integer age;




}
