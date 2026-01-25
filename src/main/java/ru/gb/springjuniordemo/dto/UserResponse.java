package ru.gb.springjuniordemo.dto;

import lombok.Getter;

@Getter
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private Integer age;

    public UserResponse(Long id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
