package com.superngb3000.weapon_shop.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateRequest {

    private String username;
    private String password;
    private String roleClient;
    private String roleManager;
    private String roleAdmin;
}
