package ru.gb.springjuniordemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springjuniordemo.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


}
