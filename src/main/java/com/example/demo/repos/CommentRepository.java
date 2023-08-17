package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    //List<Comment> findByUserId(Long userId);

    List<Comment> getByPostId(Long postId);

    List<Comment> getByUserId(Long aLong);

    @Query(value = "select 'commented on', c.post_id, u.avatar, u.user_name from "
            + "comment c left join user u on u.id = c.user_id "
            + "where c.post_id in :postIds limit 5", nativeQuery = true)
    List<Object> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);

}

