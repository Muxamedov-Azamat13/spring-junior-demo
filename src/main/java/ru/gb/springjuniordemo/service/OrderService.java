package ru.gb.springjuniordemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springjuniordemo.dto.OrderResponse;
import ru.gb.springjuniordemo.dto.UserResponse;
import ru.gb.springjuniordemo.entity.Order;
import ru.gb.springjuniordemo.entity.User;
import ru.gb.springjuniordemo.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderResponse create(User user, String product, Double price){
        Order order = new Order(product,price,user);
        repository.save(order);
        return new OrderResponse(order.getId(), product, price);
    }

    public List<OrderResponse> getByUser(Long userId){
        return repository.findByUserId(userId)
                .stream()
                .map(order -> new OrderResponse(order.getId(), order.getProductName(), order.getPrice()))
                .toList();
    }
}
