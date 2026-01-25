package ru.gb.springjuniordemo.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gb.springjuniordemo.dto.RegisterRequest;
import ru.gb.springjuniordemo.dto.UserRequest;
import ru.gb.springjuniordemo.dto.UserResponse;
import ru.gb.springjuniordemo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody RegisterRequest request){
        return userService.create(request);
    }

    @GetMapping
    public Page<UserResponse> getAll(Pageable pageable){
        return userService.getAll(pageable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }


}
