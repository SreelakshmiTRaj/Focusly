package com.example.Focusly.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Focusly.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
    Optional<User> findById(Long id);
}
