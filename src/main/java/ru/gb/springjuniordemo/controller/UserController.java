package ru.gb.springjuniordemo.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.springjuniordemo.dto.RegisterRequest;
import ru.gb.springjuniordemo.dto.UserRequest;
import ru.gb.springjuniordemo.dto.UserResponse;
import ru.gb.springjuniordemo.entity.User;
import ru.gb.springjuniordemo.service.UserService;

import java.security.Principal;

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
    public void delete(@PathVariable Long id, Principal principal){
        User user = userService.findByEmail(principal.getName());

        if (!user.getId().equals(id)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot delete another user");
        }

        userService.delete(id);
    }


}
