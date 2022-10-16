package com.devchasers.authenticationms.service;


import com.devchasers.authenticationms.repository.UserRepository;
import com.devchasers.authenticationms.entity.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service

@ComponentScan("com.devchasers.authenticationms")
public class UserServiceDetails implements UserDetailsService {
    @Autowired
    private UserRepository userRespository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new MyUserDetails(userRespository.findByEmail(userName));
    }


    @Bean
    public BCryptPasswordEncoder  bcryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


}