package com.example.demo.services;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.Users;
import com.example.demo.repos.CommentRepository;
import com.example.demo.requests.CommentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServices {
    private CommentRepository commentRepository;
    private UserServices userServices;
    private PostServices postServices;


    @Autowired
    public CommentServices(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }
        else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }
        else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }
        else {
            return commentRepository.findAll();
        }
    }

    public Comment findByCommentId(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        Users user = userServices.getUserByUserId(commentCreateRequest.getUserId());
        Post post = postServices.getPostByPostId(commentCreateRequest.getPostId());

        if(user!=null && post!=null){
            Comment commentToSave = new Comment();
            commentToSave.setId(commentCreateRequest.getId());
            commentToSave.setUser(user);
            commentToSave.setPost(post);
            commentToSave.setText(commentCreateRequest.getText());
            return commentRepository.save(commentToSave);
        }
        return null;
    }
}

//14.04
