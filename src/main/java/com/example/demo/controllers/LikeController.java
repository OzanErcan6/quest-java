package com.example.demo.controllers;

import com.example.demo.entities.Like;
import com.example.demo.requests.LikeCreateRequest;
import com.example.demo.responses.LikeResponse;
import com.example.demo.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/like")
@CrossOrigin
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId,postId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.createLike(likeCreateRequest);
    }

    @GetMapping("/{likeId}")
    public Like getLikeByLikeId(@PathVariable Long likeId){
        return likeService.findByLikeId(likeId);
    }


    @DeleteMapping("/{likeId}")
    public void deletelikeByLikeId(@PathVariable Long likeId){
        likeService.deleteLikeByLikeId(likeId);
    }
}
