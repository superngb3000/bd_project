package com.superngb3000.weapon_shop.Controllers;

import com.superngb3000.weapon_shop.Entities.Weapon;
import com.superngb3000.weapon_shop.Services.WeaponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WeaponController {

    private final WeaponService weaponService;

    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @GetMapping("/weapons")
    public String getWeapons(Model model){
        model.addAttribute("weapons", weaponService.getAllWeapons());
        return "weapons";
    }

    @PostMapping("/weapon")
    public String createWeapon(Model model,
                               @RequestParam String cadastralNumber,
                               @RequestParam String name,
                               @RequestParam String producer,
                               @RequestParam String technicalSpecifications,
                               @RequestParam Float price,
                               @RequestParam(defaultValue = "") String action){
        weaponService.createWeapon(new Weapon(cadastralNumber, name, producer, technicalSpecifications, price));
        model.addAttribute("weapons", weaponService.getAllWeapons());
        return "redirect:/weapons";
    }
}
