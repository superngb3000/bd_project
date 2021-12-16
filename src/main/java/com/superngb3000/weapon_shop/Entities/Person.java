package com.superngb3000.weapon_shop.Entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "first_name")
    private String first_name;

    @NotNull
    @Column(name = "second_name")
    private String second_name;

    @NotNull
    @Column(name = "middle_name")
    private String middle_name;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;
}
