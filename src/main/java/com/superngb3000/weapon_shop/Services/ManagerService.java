package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Entities.Manager;
import com.superngb3000.weapon_shop.Entities.Person;
import com.superngb3000.weapon_shop.Repositories.ManagerRepository;
import com.superngb3000.weapon_shop.Repositories.PersonRepository;
import com.superngb3000.weapon_shop.Requests.ManagerUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final PersonRepository personRepository;

    public ManagerService(ManagerRepository managerRepository, PersonRepository personRepository) {
        this.managerRepository = managerRepository;
        this.personRepository = personRepository;
    }

    public Manager getManager(Integer id){
        return managerRepository.findById(id).orElse(null);
    }

    public List<Manager> getAllManagers(){
        return managerRepository.findAll();
    }

    public boolean createManager(Integer personId, Manager manager){
        Optional<Person> optionalPerson = personRepository.findById(personId);

        if (!optionalPerson.isPresent() || managerRepository.findByPersonId(personId) != null)
            return false;

        manager.setPerson(optionalPerson.get());
        managerRepository.save(manager);
        return true;
    }

    public Manager updateManager(Integer id, ManagerUpdateRequest managerUpdateRequest){
        Optional<Manager> optionalManager = managerRepository.findById(id);

        if (optionalManager.isPresent()){

            Manager manager = optionalManager.get();
            String license = managerUpdateRequest.getLicense();
            String employmentContract = managerUpdateRequest.getEmploymentContract();

            if (license != null && !license.isEmpty())
                manager.setLicense(license);

            if (employmentContract != null && !employmentContract.isEmpty())
                manager.setLicense(employmentContract);

            managerRepository.save(manager);
        }

        return optionalManager.orElse(null);
    }

    public Manager deleteManager(Integer id){
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent())
            managerRepository.deleteById(id);
        return manager.orElse(null);
    }
}
