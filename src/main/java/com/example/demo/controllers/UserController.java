package com.example.demo.controllers;

import com.example.demo.entities.Users;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;
    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public Users createUser(@RequestBody Users newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    @PutMapping("/{userId}")
    public Users updateUserById(@PathVariable Long userId, @RequestBody Users newUser){
        Optional<Users> user = userRepository.findById(userId);
        if(user.isPresent()){
            Users foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }
        else
            return null;
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId){
        userRepository.deleteById(userId);
    }


}
