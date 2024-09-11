package com.rkisuru.fitnesshub.repository;

import com.rkisuru.fitnesshub.entity.User;
import com.rkisuru.fitnesshub.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
