package com.superngb3000.weapon_shop.Controllers;

import com.superngb3000.weapon_shop.Entities.Person;
import com.superngb3000.weapon_shop.Services.ClientService;
import com.superngb3000.weapon_shop.Services.ManagerService;
import com.superngb3000.weapon_shop.Services.PersonService;
import com.superngb3000.weapon_shop.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    private final PersonService personService;
    private final UserService userService;
    private final ClientService clientService;
    private final ManagerService managerService;

    public PersonController(PersonService personService, UserService userService, ClientService clientService, ManagerService managerService) {
        this.personService = personService;
        this.userService = userService;
        this.clientService = clientService;
        this.managerService = managerService;
    }

    @GetMapping("/persons")
    public String getPersons(Model model){
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("managers", managerService.getAllManagers());
        return "persons";
    }

    @PostMapping("/person")
    public String createPerson(Model model,
                             @RequestParam String firstName,
                             @RequestParam String secondName,
                             @RequestParam String middleName,
                             @RequestParam String email,
                             @RequestParam String phoneNumber){
        personService.createPerson(new Person(firstName, secondName, middleName, email, phoneNumber));
        return "persons";
    }
}
