package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long>{

    List<Like> findByUserIdAndPostId(Long aLong, Long aLong1);

    List<Like> findByUserId(Long aLong);

    List<Like> findByPostId(Long aLong);

    @Query(value = 	"select 'liked', l.post_id, u.avatar, u.user_name from "
            + "p_like l left join user u on u.id = l.user_id "
            + "where l.post_id in :postIds limit 5", nativeQuery = true)
    List<Object> findUserLikesByPostId(@Param("postIds") List<Long> postIds);
}
