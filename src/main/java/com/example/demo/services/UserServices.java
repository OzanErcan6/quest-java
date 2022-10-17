package com.example.demo.services;

import com.example.demo.entities.Users;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users createUser(Users newUser){
        return userRepository.save(newUser);
    }

    public Users getUserById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public Users updateUserById(Long userId, Users newUser){
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

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
}
