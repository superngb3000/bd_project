package com.superngb3000.weapon_shop.Enums;

public enum RoleEnum {
    ROLE_ADMIN(1),
    ROLE_MANAGER(2),
    ROLE_CLIENT(3);

    private Integer id;

    RoleEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
