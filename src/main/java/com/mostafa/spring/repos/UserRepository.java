package com.mostafa.spring.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mostafa.spring.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
 
}
