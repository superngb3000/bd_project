package com.superngb3000.weapon_shop.Controllers;

import com.superngb3000.weapon_shop.Entities.Client;
import com.superngb3000.weapon_shop.Services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String getClients(Model model){
        model.addAttribute("clients", clientService.getAllClients());
        return "clients";
    }

    @PostMapping("/client")
    public String createClient(Model model,
                               @RequestParam Integer personId,
                               @RequestParam String license){
        clientService.createClient(personId, new Client(license));
        return "clients";
    }
}
