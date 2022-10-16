package com.devchasers.authenticationms.service;




import com.devchasers.authenticationms.entity.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findByUserId(String id);

    User saveOrUpdateUser(User student);

    void deleteUserById(String id);
}
