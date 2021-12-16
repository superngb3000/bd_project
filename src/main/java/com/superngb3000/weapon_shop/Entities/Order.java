package com.superngb3000.weapon_shop.Entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "price")
    private Float price;

    @NotNull
    @Column(name = "date")
    private Date date;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="orders_weapons",
            joinColumns = {@JoinColumn(name="order_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="weapon_id", referencedColumnName="id")}
    )
    private List<Weapon> weapons;
}
