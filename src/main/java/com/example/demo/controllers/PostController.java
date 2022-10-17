package com.example.demo.controllers;

import com.example.demo.entities.Post;
import com.example.demo.entities.Users;
import com.example.demo.requests.PostCreateRequest;
import com.example.demo.requests.PostUpdateRequest;
import com.example.demo.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostServices postServices;

    @Autowired
    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }

    // works for both URIs /posts ve /posts?userId={userId}
    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postServices.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPostByPostId(@PathVariable Long postId){
        return postServices.getPostByPostId(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPostCreateRequest){
        return postServices.createPost(newPostCreateRequest);
    }

    @PutMapping("/{postId}")
    public Post updatePostByPostId(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest){
        return postServices.updatePostByPostId(postId, postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePostByPostId(@PathVariable Long postId){

        }

}
