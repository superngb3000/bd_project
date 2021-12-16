package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Entities.Order;
import com.superngb3000.weapon_shop.Entities.Weapon;
import com.superngb3000.weapon_shop.Repositories.ClientRepository;
import com.superngb3000.weapon_shop.Repositories.ManagerRepository;
import com.superngb3000.weapon_shop.Repositories.OrderRepository;
import com.superngb3000.weapon_shop.Repositories.WeaponRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final WeaponRepository weaponRepository;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, ManagerRepository managerRepository, WeaponRepository weaponRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.weaponRepository = weaponRepository;
    }

    public Order getOrder(Integer id){
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public boolean createOrder(Order order){
        if (!clientRepository.findById(order.getClient().getId()).isPresent())
            return false;

        if (!managerRepository.findById(order.getManager().getId()).isPresent())
            return false;

        if (order.getPrice() == null)
            order.setPrice(0.0f);

        orderRepository.save(order);
        return true;
    }

    public Order addWeapon(Integer orderId, Integer weaponId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<Weapon> optionalWeapon = weaponRepository.findById(weaponId);

        if (optionalOrder.isPresent() && optionalWeapon.isPresent()){
            Order order = optionalOrder.get();
            Weapon weapon = optionalWeapon.get();
            List<Weapon> weapons = order.getWeapons();
            Float price = order.getPrice();

            weapons.add(weapon);
            price += weapon.getPrice();

            order.setWeapons(weapons);
            order.setPrice(price);

            orderRepository.save(order);
        }

        return optionalOrder.orElse(null);
    }

//    public Order updateOrder(Integer id, OrderUpdateRequest orderUpdateRequest){
//        Optional<Order> optionalOrder = orderRepository.findById(id);
//
//
//        return optionalOrder.orElse(null);
//    }

    public Order deleteOrder(Integer id){
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent())
            orderRepository.deleteById(id);
        return order.orElse(null);
    }
}
