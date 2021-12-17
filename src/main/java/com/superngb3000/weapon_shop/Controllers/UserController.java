package com.superngb3000.weapon_shop.Controllers;

import com.superngb3000.weapon_shop.Entities.User;
import com.superngb3000.weapon_shop.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model){
//        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("users", userService.getUsersSQL());
        return "users";
    }

    @PostMapping("/user")
    public String createUser(Model model,
                               @RequestParam Integer personId,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String roleClient,
                               @RequestParam String roleManager,
                               @RequestParam String roleAdmin){
        userService.createUser(personId, new User(username, password), roleClient, roleManager, roleAdmin);
        return "redirect:/users";
    }
}
