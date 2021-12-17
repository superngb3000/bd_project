package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Entities.Person;
import com.superngb3000.weapon_shop.Entities.Role;
import com.superngb3000.weapon_shop.Entities.User;
import com.superngb3000.weapon_shop.Enums.RoleEnum;
import com.superngb3000.weapon_shop.Repositories.*;
import com.superngb3000.weapon_shop.Requests.UserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, RoleRepository roleRepository, PersonRepository personRepository, ClientRepository clientRepository, ManagerRepository managerRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
    }

    public User getUser(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        return userRepository.findAll();
    }

    public boolean createUser(Integer personId, User user, String roleClient, String roleManager, String roleAdmin){
        Optional<Person> optionalPerson = personRepository.findById(personId);

        if (!optionalPerson.isPresent() || userRepository.findByPersonId(personId) != null)
            return false;

        List<Role> roles = new ArrayList<>();
        if (roleClient.equals(RoleEnum.ROLE_CLIENT.name()) && clientRepository.findByPersonId(personId) != null)
            roles.add(roleRepository.findById(RoleEnum.ROLE_CLIENT.getId()).get());

        if (roleManager.equals(RoleEnum.ROLE_MANAGER.name()) && managerRepository.findByPersonId(personId) != null)
            roles.add(roleRepository.findById(RoleEnum.ROLE_MANAGER.getId()).get());

        if (roleManager.equals(RoleEnum.ROLE_ADMIN.name()))
            roles.add(roleRepository.findById(RoleEnum.ROLE_ADMIN.getId()).get());

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setPerson(optionalPerson.get());
        userRepository.save(user);
        return true;
    }

    public User updateUser(Integer id, UserUpdateRequest userUpdateRequest){
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){

            User user = optionalUser.get();
            String username = userUpdateRequest.getUsername();
            String password = userUpdateRequest.getPassword();

            if (username != null && !username.isEmpty())
                user.setUsername(username);

            if (password != null && !password.isEmpty())
                user.setUsername(password);

            List<Role> roles = user.getRoles();
            boolean alreadyExistRoleClient = false;
            boolean alreadyExistRoleManager = false;
            boolean alreadyExistRoleAdmin = false;
            for (Role role:user.getRoles()){
                if (role.getName().equals(RoleEnum.ROLE_CLIENT.name())) {
                    alreadyExistRoleClient = true;
                }
                if (role.getName().equals(RoleEnum.ROLE_MANAGER.name())) {
                    alreadyExistRoleManager = true;
                }
                if (role.getName().equals(RoleEnum.ROLE_ADMIN.name())) {
                    alreadyExistRoleAdmin = true;
                }
            }
            if (!userUpdateRequest.getRoleClient().equals("")){
                if (!alreadyExistRoleClient) roles.add(roleRepository.findById(RoleEnum.ROLE_CLIENT.getId()).get());
            }
            else{
                if(alreadyExistRoleClient) roles.remove(roleRepository.findById(RoleEnum.ROLE_CLIENT.getId()).get());
            }
            if (!userUpdateRequest.getRoleManager().equals("")){
                if (!alreadyExistRoleManager) roles.add(roleRepository.findById(RoleEnum.ROLE_MANAGER.getId()).get());
            }
            else{
                if (alreadyExistRoleManager) roles.remove(roleRepository.findById(RoleEnum.ROLE_MANAGER.getId()).get());
            }
            if (!userUpdateRequest.getRoleAdmin().equals("")){
                if (!alreadyExistRoleAdmin) roles.add(roleRepository.findById(RoleEnum.ROLE_ADMIN.getId()).get());
            }
            else{
                if (alreadyExistRoleAdmin) roles.remove(roleRepository.findById(RoleEnum.ROLE_ADMIN.getId()).get());
            }
            user.setRoles(roles);

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            userRepository.save(user);
        }

        return optionalUser.orElse(null);
    }

    public User deleteUser(Integer id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            userRepository.deleteById(id);
        return user.orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }
}
