package com.superngb3000.weapon_shop.Controllers;

import com.superngb3000.weapon_shop.Entities.Manager;
import com.superngb3000.weapon_shop.Services.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/managers")
    public String getManagers(Model model){
        model.addAttribute("managers", managerService.getAllManagers());
        return "managers";
    }

    @PostMapping("/manager")
    public String createManager(Model model,
                               @RequestParam Integer personId,
                               @RequestParam String license,
                               @RequestParam String employmentContract){
        managerService.createManager(personId, new Manager(license, employmentContract));
        return "redirect:/managers";
    }
}
