package com.rkisuru.food.recipe.repository;

import com.rkisuru.food.recipe.model.User;
import com.rkisuru.food.recipe.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
