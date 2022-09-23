package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

}
