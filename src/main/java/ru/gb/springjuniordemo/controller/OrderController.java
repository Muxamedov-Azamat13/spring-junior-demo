package ru.gb.springjuniordemo.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.gb.springjuniordemo.dto.OrderResponse;
import ru.gb.springjuniordemo.dto.UserRequest;
import ru.gb.springjuniordemo.dto.UserResponse;
import ru.gb.springjuniordemo.entity.User;
import ru.gb.springjuniordemo.service.OrderService;
import ru.gb.springjuniordemo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/orders")
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse create(
            @PathVariable Long id, @RequestBody OrderResponse request ) {

        User user = userService.findById(id);
        return orderService.create(user, request.getProductName(), request.getPrice());
    }

    @GetMapping
    public List<OrderResponse> getOrders(@PathVariable Long id) {
        return orderService.getByUser(id);
    }
}
