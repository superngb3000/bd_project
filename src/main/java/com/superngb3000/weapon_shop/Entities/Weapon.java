package com.superngb3000.weapon_shop.Entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weapons")
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "cadastral_number")
    private String cadastralNumber;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "producer")
    private String producer;

    @NotNull
    @Column(name = "technical_specifications")
    private String technicalSpecifications;

    @NotNull
    @Column(name = "price")
    private Float price;

    @Transient
    @ManyToMany(mappedBy = "weapons")
    private List<Order> orders;
}
