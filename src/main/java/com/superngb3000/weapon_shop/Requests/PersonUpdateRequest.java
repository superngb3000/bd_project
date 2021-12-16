package com.superngb3000.weapon_shop.Requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonUpdateRequest {

    private String firstName;
    private String secondName;
    private String middleName;
    private String email;
    private String phoneNumber;
}
