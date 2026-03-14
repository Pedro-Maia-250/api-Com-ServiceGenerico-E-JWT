package com.lunarvoid.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.lunarvoid.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    public Optional<UserDetails> findByUsername(String username);
}
