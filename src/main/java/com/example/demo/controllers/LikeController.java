package com.example.demo.controllers;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Like;
import com.example.demo.requests.LikeCreateRequest;
import com.example.demo.responses.LikeResponse;
import com.example.demo.services.LikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/like")
public class LikeController {
    private LikeServices likeServices;

    @Autowired
    public LikeController(LikeServices likeServices) {
        this.likeServices = likeServices;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeServices.getAllLikesWithParam(userId,postId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeServices.createLike(likeCreateRequest);
    }

    @GetMapping("/{likeId}")
    public Like getLikeByLikeId(@PathVariable Long likeId){
        return likeServices.findByLikeId(likeId);
    }


    @DeleteMapping("/{likeId}")
    public void deletelikeByLikeId(@PathVariable Long likeId){
        likeServices.deleteLikeByLikeId(likeId);
    }
}
