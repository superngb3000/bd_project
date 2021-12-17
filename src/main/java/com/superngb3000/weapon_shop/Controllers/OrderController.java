package com.superngb3000.weapon_shop.Controllers;

import com.superngb3000.weapon_shop.Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final WeaponService weaponService;
    private final PersonService personService;
    private final ClientService clientService;
    private final ManagerService managerService;

    public OrderController(OrderService orderService, WeaponService weaponService, PersonService personService, ClientService clientService, ManagerService managerService) {
        this.orderService = orderService;
        this.weaponService = weaponService;
        this.personService = personService;
        this.clientService = clientService;
        this.managerService = managerService;
    }

    @GetMapping("/orders")
    public String getOrders(Model model){
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("weapons", weaponService.getAllWeapons());
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("managers", managerService.getAllManagers());
        return "orders";
    }
}
