package ru.gb.springjuniordemo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.springjuniordemo.dto.OrderResponse;
import ru.gb.springjuniordemo.dto.UserRequest;
import ru.gb.springjuniordemo.dto.UserResponse;
import ru.gb.springjuniordemo.entity.User;
import ru.gb.springjuniordemo.service.OrderService;
import ru.gb.springjuniordemo.service.UserService;

import java.security.Principal;
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
    public OrderResponse create(@PathVariable Long id,
            @RequestBody OrderResponse request, Principal principal) {

        User user = userService.findByEmail(principal.getName());

        if (!user.getId().equals(id)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot create orders for another users!");
        }

        return orderService.create(user, request.getProductName(), request.getPrice());
    }

    @GetMapping
    public List<OrderResponse> myOrders(@PathVariable Long id, Principal principal){
        User user = userService.findByEmail(principal.getName());

        if (!user.getId().equals(id)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot get orders another users!");
        }
        return orderService.getByUser(user.getId());
    }
}
