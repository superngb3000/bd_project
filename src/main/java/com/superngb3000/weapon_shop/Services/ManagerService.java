package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Repositories.ManagerRepository;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }
}
