package ru.gb.springjuniordemo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.springjuniordemo.repository.UserRepository;
import ru.gb.springjuniordemo.security.JwtService;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder password;
    private final JwtService jwtService;
}
