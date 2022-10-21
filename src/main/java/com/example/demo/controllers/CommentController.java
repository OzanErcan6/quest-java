package com.example.demo.controllers;

import com.example.demo.entities.Comment;
import com.example.demo.requests.CommentCreateRequest;
import com.example.demo.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentServices commentServices;

    @Autowired
    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    // /comments
    // /comments?postId={postId}
    // /comments?userId={userId}
    // /comments?userId={userId}&postId={postId}
    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentServices.getAllCommentsWithParam(userId,postId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentServices.createComment(commentCreateRequest);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentByCommentId(@PathVariable Long commentId){
        return commentServices.findByCommentId(commentId);
    }
}
