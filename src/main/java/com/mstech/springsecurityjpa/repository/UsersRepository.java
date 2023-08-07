package com.mstech.springsecurityjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mstech.springsecurityjpa.models.Users;


public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserName(String userName);
}
