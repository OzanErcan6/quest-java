package com.example.demo.controllers;

import com.example.demo.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private PostServices postServices;

    @Autowired
    public CommentController(PostServices postServices) {
        this.postServices = postServices;
    }
}