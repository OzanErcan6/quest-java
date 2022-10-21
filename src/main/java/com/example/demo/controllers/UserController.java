package com.example.demo.controllers;

import com.example.demo.entities.Users;
import com.example.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserServices userService;

    @Autowired
    public UserController(UserServices userService){
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public Users createUser(@RequestBody Users newUser){
        return userService.createUser(newUser);
    }

    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable Long userId){
        return userService.getUserByUserId(userId);
    }

    @PutMapping("/{userId}")
    public Users updateUserById(@PathVariable Long userId, @RequestBody Users newUser){
        return userService.updateUserById(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }


}
