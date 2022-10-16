package com.devchasers.authenticationms.service;



import com.devchasers.authenticationms.entity.Role;
import com.devchasers.authenticationms.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private com.devchasers.authenticationms.repository.UserRepository UserRepository;

    @Autowired
    ApplicationContext context;




    @Override
    public List<User> findAll() {
        return UserRepository.findAll();
    }

    @Override
    public User findByUserId(String id) {
        return UserRepository.findById(id).orElse(null);
    }


    @Override
    public User saveOrUpdateUser(User User) {
        PasswordEncoder bcryptPasswordEncoder = context.getBean(PasswordEncoder.class);
        if(UserRepository.findAll().isEmpty())
            User.setRole(Role.ADMIN);
        else
            User.setRole(Role.USER);
        User.setPassword(bcryptPasswordEncoder.encode(User.getPassword()));
        return UserRepository.save(User);
    }

    @Override
    public void deleteUserById(String id) {
        UserRepository.deleteById(id);
    }

    public User findByEmail(String email) {
        return UserRepository.findByEmail(email);
    }

}
