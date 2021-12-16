package com.superngb3000.weapon_shop.Repositories;

import com.superngb3000.weapon_shop.Entities.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository extends JpaRepository<Weapon, Integer> {
}
