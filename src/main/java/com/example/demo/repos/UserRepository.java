package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Users;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long>{
}
