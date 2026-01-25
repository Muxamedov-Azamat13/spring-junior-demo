package ru.gb.springjuniordemo.dto;
import lombok.Getter;

@Getter
public class RegisterRequest {

    private String name;
    private String email;
    private Integer age;
    private String password;

}
