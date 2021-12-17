package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Entities.Weapon;
import com.superngb3000.weapon_shop.Repositories.WeaponRepository;
import com.superngb3000.weapon_shop.Requests.WeaponUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WeaponService {

    @PersistenceContext
    EntityManager em;

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

    public void insertWeaponSql(Weapon weapon){
        em.createNativeQuery("insert into weapons (cadastral_number, name, producer, technical_specifications, price)" +
                        " value ( :wnum, :wname, :wprod, :wspec, :wpr);")
                .setParameter("wnum", weapon.getCadastralNumber())
                .setParameter("wname", weapon.getName())
                .setParameter("wprod", weapon.getProducer())
                .setParameter("wspec", weapon.getTechnicalSpecifications())
                .setParameter("wpr", weapon.getPrice())
                .executeUpdate();
    }

    public List getWeaponsSQL(){
        return em.createNativeQuery("select * from weapons;", Weapon.class).getResultList();
    }

    public void deleteWeaponSQL(Integer id){
        if (weaponRepository.findById(id).isPresent()) {
            em.createNativeQuery("delete from weapons where id=:param_id",Weapon.class)
                    .setParameter("param_id",id)
                    .executeUpdate();
        }
    }
}
