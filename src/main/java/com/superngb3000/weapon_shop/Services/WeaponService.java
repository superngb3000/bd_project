package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Repositories.WeaponRepository;
import org.springframework.stereotype.Service;

@Service
public class WeaponService {

    private final WeaponRepository weaponRepository;

    public WeaponService(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }
}
