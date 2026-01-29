package ru.gb.springjuniordemo.service;

import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.springjuniordemo.dto.RegisterRequest;
import ru.gb.springjuniordemo.dto.UserRequest;
import ru.gb.springjuniordemo.dto.UserResponse;
import ru.gb.springjuniordemo.entity.User;
import ru.gb.springjuniordemo.exception.NotFoundException;
import ru.gb.springjuniordemo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse create(RegisterRequest request){
       User user = new User(
               request.getName(),
               request.getEmail(),
               request.getAge(),
               passwordEncoder.encode(request.getPassword())
       );
       repository.save(user);
       return map(user);
    }

    public UserResponse map(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }

    public Page<UserResponse> getAll(Pageable pageable){
        return repository.findAll(pageable).map(this::map);
    }

    public User findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void delete(Long id){
        repository.delete(findById(id));
    }

    public User findByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email" + email + "not found!"));
    }
}
