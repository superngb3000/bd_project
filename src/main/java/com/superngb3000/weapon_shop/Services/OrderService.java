package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private  final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
