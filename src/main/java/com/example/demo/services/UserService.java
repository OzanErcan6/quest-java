package com.example.demo.services;

import com.example.demo.entities.Users;
import com.example.demo.repos.CommentRepository;
import com.example.demo.repos.LikeRepository;
import com.example.demo.repos.PostRepository;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    LikeRepository likeRepository;
    CommentRepository commentRepository;
    PostRepository postRepository;

    public UserService(UserRepository userRepository, LikeRepository likeRepository,
                       CommentRepository commentRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users createUser(Users newUser) {
        Users newU;
        try {
            newU = userRepository.save(newUser);
        } catch (Exception e) {
            throw e;
        }
        return newU;
    }

    public Users getUserByUserId(Long userId){
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

    public Users getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<Object> getUserActivity(Long userId) {
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if(postIds.isEmpty())
            return null;
        List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
        List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
        List<Object> result = new ArrayList<>();
        result.addAll(comments);
        result.addAll(likes);
        return result;
    }
}
