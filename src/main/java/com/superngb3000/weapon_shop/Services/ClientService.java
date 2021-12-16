package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
