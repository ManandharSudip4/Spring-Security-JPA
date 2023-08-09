package com.mstech.springsecurityjpa.repository;

import com.mstech.springsecurityjpa.models.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
  Optional<Users> findByUserName(String userName);
}
