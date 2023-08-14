package com.example.demo.services;

import com.example.demo.entities.Post;
import com.example.demo.entities.Users;
import com.example.demo.repos.PostRepository;
import com.example.demo.requests.PostCreateRequest;
import com.example.demo.requests.PostUpdateRequest;
import com.example.demo.responses.PostResponse;

//import com.example.demo.responses.LikeResponse;
//import com.example.demo.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import java.util.stream.Collectors;

@Service
public class PostServices {

    private PostRepository postRepository;

    private UserServices userServices;
    //private LikeServices likeServices;

    @Autowired
    public PostServices(PostRepository postRepository, UserServices userServices){//, LikeServices likeServices) {
        this.postRepository = postRepository;
        this.userServices = userServices;
        //this.likeServices = likeServices;

    }
    //@Transactional
    public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> list;
		if(userId.isPresent()) {
             list = postRepository.findByUserId(userId.get());
		}
        else{
            list = postRepository.findAll();
        }
		return list.stream().map(p -> new PostResponse(p)).collect(Collectors.toList());
	}

/*    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        }else
            list = postRepository.findAll();
        return list.stream().map(p -> {
            List<LikeResponse> likes = likeServices.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponse(p, likes);}).collect(Collectors.toList());
    }*/

    public Post getPostByPostId(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(PostCreateRequest newPostCreateRequest) {
        Users user = userServices.getUserByUserId(newPostCreateRequest.getUserId());
        if(user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostCreateRequest.getId());
        toSave.setText(newPostCreateRequest.getText());
        toSave.setTitle(newPostCreateRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updatePostByPostId(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(postUpdateRequest.getText());
            toUpdate.setTitle(postUpdateRequest.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }

        return null;
    }

    public void deletePostByPostId(Long postId) {
        postRepository.deleteById(postId);
    }
}
