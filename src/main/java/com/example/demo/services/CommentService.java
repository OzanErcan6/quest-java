package com.example.demo.services;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.Users;
import com.example.demo.repos.CommentRepository;
import com.example.demo.requests.CommentCreateRequest;
import com.example.demo.requests.CommentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Transactional
    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }
        else if(userId.isPresent()){
            return commentRepository.getByUserId(userId.get());
        }
        else if(postId.isPresent()){
            return commentRepository.getByPostId(postId.get());
        }
        else {
            return commentRepository.findAll();
        }
    }

    public Comment findByCommentId(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }
    @Transactional
    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        Users user = userService.getUserByUserId(commentCreateRequest.getUserId());
        Post post = postService.getPostByPostId(commentCreateRequest.getPostId());

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

    public Comment updateCommentByCommentId(Long commentId, CommentUpdateRequest request) {
        Optional <Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);
        }
        return null;
    }

    public void deleteCommentByCommentId(Long commentId) {
        Optional <Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            commentRepository.deleteById(commentId);
        }
    }
}
