package com.superngb3000.weapon_shop.Repositories;

import com.superngb3000.weapon_shop.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
