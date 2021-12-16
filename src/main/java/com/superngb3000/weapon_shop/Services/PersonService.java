package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Entities.Person;
import com.superngb3000.weapon_shop.Repositories.PersonRepository;
import com.superngb3000.weapon_shop.Requests.PersonUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPerson(Integer id){
        return personRepository.findById(id).orElse(null);
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public boolean createPerson(Person person){
        personRepository.save(person);
        return true;
    }

    public Person updatePerson(Integer id, PersonUpdateRequest personUpdateRequest){
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()){

            Person person = optionalPerson.get();
            String firstName = personUpdateRequest.getFirstName();
            String secondName = personUpdateRequest.getSecondName();
            String middleName = personUpdateRequest.getMiddleName();
            String email = personUpdateRequest.getEmail();
            String phoneNumber = personUpdateRequest.getPhoneNumber();

            if (firstName != null && !firstName.isEmpty())
                person.setFirstName(firstName);

            if (secondName != null && !secondName.isEmpty())
                person.setSecondName(secondName);

            if (middleName != null && !middleName.isEmpty())
                person.setMiddleName(middleName);

            if (email != null && !email.isEmpty())
                person.setEmail(email);

            if (phoneNumber != null && !phoneNumber.isEmpty())
                person.setPhoneNumber(phoneNumber);

            personRepository.save(person);
        }

        return optionalPerson.orElse(null);
    }

    public Person deletePerson(Integer id){
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent())
            personRepository.deleteById(id);
        return person.orElse(null);
    }
}
