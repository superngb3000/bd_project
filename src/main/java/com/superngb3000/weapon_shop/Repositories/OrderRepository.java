package com.superngb3000.weapon_shop.Repositories;

import com.superngb3000.weapon_shop.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
