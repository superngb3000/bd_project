package com.superngb3000.weapon_shop.Repositories;

import com.superngb3000.weapon_shop.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Client findByPersonId(Integer personId);
}
