package com.example.demo.controllers;

import com.example.demo.entities.Post;
import com.example.demo.requests.PostCreateRequest;
import com.example.demo.requests.PostUpdateRequest;
import com.example.demo.responses.PostResponse;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // works for both URIs /posts ve /posts?userId={userId}
    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPostByPostId(@PathVariable Long postId){
        return postService.getPostByPostId(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPostCreateRequest){
        return postService.createPost(newPostCreateRequest);
    }

    @PutMapping("/{postId}")
    public Post updatePostByPostId(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updatePostByPostId(postId, postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePostByPostId(@PathVariable Long postId){
        postService.deletePostByPostId(postId);
    }

}
