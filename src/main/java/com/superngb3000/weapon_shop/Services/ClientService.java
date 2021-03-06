package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Entities.Client;
import com.superngb3000.weapon_shop.Entities.Person;
import com.superngb3000.weapon_shop.Repositories.ClientRepository;
import com.superngb3000.weapon_shop.Repositories.PersonRepository;
import com.superngb3000.weapon_shop.Requests.ClientUpdateRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;

    public ClientService(ClientRepository clientRepository, PersonRepository personRepository) {
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
    }

    public Client getClient(Integer id){
        return clientRepository.findById(id).orElse(null);
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public boolean createClient(Integer personId, Client client){
        Optional<Person> optionalPerson = personRepository.findById(personId);

        if (!optionalPerson.isPresent() || clientRepository.findByPersonId(personId) != null)
            return false;

        client.setPerson(optionalPerson.get());
        clientRepository.save(client);
        return true;
    }

    public Client updateClient(Integer id, ClientUpdateRequest clientUpdateRequest){
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()){

            Client client = optionalClient.get();
            String license = clientUpdateRequest.getLicense();

            if (license != null && !license.isEmpty())
                client.setLicense(license);

            clientRepository.save(client);
        }

        return optionalClient.orElse(null);
    }

    public Client deleteClient(Integer id){
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent())
            clientRepository.deleteById(id);
        return client.orElse(null);
    }
}
