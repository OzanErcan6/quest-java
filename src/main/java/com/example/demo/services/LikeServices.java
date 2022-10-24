package com.example.demo.services;

import com.example.demo.entities.Like;
import com.example.demo.entities.Post;
import com.example.demo.entities.Users;
import com.example.demo.repos.LikeRepository;
import com.example.demo.requests.LikeCreateRequest;
import com.example.demo.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeServices {

    private LikeRepository likeRepository;
    private UserServices userServices;
    private PostServices postServices;

    public LikeServices(LikeRepository likeRepository, UserServices userServices, PostServices postServices) {
        this.likeRepository = likeRepository;
        this.userServices = userServices;
        this.postServices = postServices;
    }


    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }else
            list = likeRepository.findAll();
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    // add the feature if a user liked a post could not like again
    public Like createLike(LikeCreateRequest likeCreateRequest) {
        Users user = userServices.getUserByUserId(likeCreateRequest.getUserId());
        Post post = postServices.getPostByPostId(likeCreateRequest.getPostId());

        if(user!=null && post!=null){
            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeRepository.save(likeToSave);
        }
        return null;
    }

    public Like findByLikeId(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }


    public void deleteLikeByLikeId(Long likeId) {
        Optional <Like> like = likeRepository.findById(likeId);
        if(like.isPresent()){
            likeRepository.deleteById(likeId);
        }
    }
}
