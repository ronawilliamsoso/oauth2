package com.zoro.oauth.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.zoro.oauth.model.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String username);
}
