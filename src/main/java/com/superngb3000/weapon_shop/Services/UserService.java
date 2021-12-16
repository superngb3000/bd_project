package com.superngb3000.weapon_shop.Services;

import com.superngb3000.weapon_shop.Entities.Role;
import com.superngb3000.weapon_shop.Entities.User;
import com.superngb3000.weapon_shop.Enums.RoleEnum;
import com.superngb3000.weapon_shop.Repositories.PersonRepository;
import com.superngb3000.weapon_shop.Repositories.RoleRepository;
import com.superngb3000.weapon_shop.Repositories.UserRepository;
import com.superngb3000.weapon_shop.Requests.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
    }

    public User getUser(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public boolean createUser(User user){
        if (!personRepository.findById(user.getPerson().getId()).isPresent())
            return false;

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
}
