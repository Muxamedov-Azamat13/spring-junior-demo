package ru.gb.springjuniordemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springjuniordemo.entity.Order;
import ru.gb.springjuniordemo.entity.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
