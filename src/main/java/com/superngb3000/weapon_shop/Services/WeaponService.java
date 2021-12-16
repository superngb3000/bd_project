package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Entities.Weapon;
import com.superngb3000.weapon_shop.Repositories.WeaponRepository;
import com.superngb3000.weapon_shop.Requests.WeaponUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeaponService {

    private final WeaponRepository weaponRepository;

    public WeaponService(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    public Weapon getWeapon(Integer id){
        return weaponRepository.findById(id).orElse(null);
    }

    public List<Weapon> getAllWeapons(){
        return weaponRepository.findAll();
    }

    public boolean createWeapon(Weapon weapon){
        weaponRepository.save(weapon);
        return true;
    }

    public Weapon updateWeapon(Integer id, WeaponUpdateRequest weaponUpdateRequest){
        Optional<Weapon> optionalWeapon = weaponRepository.findById(id);

        if (optionalWeapon.isPresent()){

            Weapon weapon = optionalWeapon.get();
            Float price = weaponUpdateRequest.getPrice();

            if (price != null)
                weapon.setPrice(price);

            weaponRepository.save(weapon);
        }

        return optionalWeapon.orElse(null);
    }

    public Weapon deleteWeapon(Integer id){
        Optional<Weapon> weapon = weaponRepository.findById(id);
        if (weapon.isPresent())
            weaponRepository.deleteById(id);
        return weapon.orElse(null);
    }
}
