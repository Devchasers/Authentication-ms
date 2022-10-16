package com.devchasers.authenticationms.controller;


import com.devchasers.authenticationms.entity.User;
import com.devchasers.authenticationms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Environment environment;

    @GetMapping("")
    public Map<String,String> userMS() {
        return Map.of("message", "User microservice is up and running on port: " + environment.getProperty("local.server.port") +" ☕🍵");
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        try{
            Optional<User> user = Optional.ofNullable(userService.findByUserId(id));
            return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity saveUser(@RequestBody User user) {
        try{
            User newUser = userService.saveOrUpdateUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("duplicate key email or phone");
        }
    }

    @PutMapping("")
    public ResponseEntity updateUser(@RequestBody User user) {
        try{
            User newUser = userService.saveOrUpdateUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("duplicate key email or phone");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        try{
            if(userService.findByUserId(id) == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body("delete success");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
