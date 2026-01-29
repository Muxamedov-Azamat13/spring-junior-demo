package ru.gb.springjuniordemo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.springjuniordemo.dto.AuthResponse;
import ru.gb.springjuniordemo.dto.LoginRequest;
import ru.gb.springjuniordemo.dto.RegisterRequest;
import ru.gb.springjuniordemo.entity.User;
import ru.gb.springjuniordemo.repository.UserRepository;
import ru.gb.springjuniordemo.security.JwtService;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder password;
    private final JwtService jwtService;

    public AuthService(UserRepository repository, PasswordEncoder password, JwtService jwtService) {
        this.repository = repository;
        this.password = password;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request){
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getAge(),
                password.encode(request.getPassword())
        );

        repository.save(user);
    }

    public AuthResponse login(LoginRequest request){
        User user = repository.findByEmail(request.email)
                .orElseThrow();

        if (!password.matches(request.password, user.getPassword())){
            throw new RuntimeException("Wrong password!");
        }

        return new AuthResponse(jwtService.generateToken(user.getEmail()));
    }
}
