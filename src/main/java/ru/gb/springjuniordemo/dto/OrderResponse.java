package ru.gb.springjuniordemo.dto;

import lombok.Getter;
import ru.gb.springjuniordemo.entity.User;

@Getter
public class OrderResponse {

    private Long id;
    private String productName;
    private Double price;

    public OrderResponse(Long id, String productName, Double price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }
}
