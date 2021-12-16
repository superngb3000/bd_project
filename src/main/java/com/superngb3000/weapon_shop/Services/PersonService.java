package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Repositories.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
