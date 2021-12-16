package com.superngb3000.weapon_shop.Repositories;

import com.superngb3000.weapon_shop.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
