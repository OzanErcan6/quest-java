package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Like;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long>{

    List<Like> findByUserIdAndPostId(Long aLong, Long aLong1);

    List<Like> findByUserId(Long aLong);

    List<Like> findByPostId(Long aLong);
}
