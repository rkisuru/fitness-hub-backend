package com.rkisuru.food.recipe.controller;

import com.rkisuru.food.recipe.model.User;
import com.rkisuru.food.recipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/{user_id}")
    public Optional<User> getUser(@PathVariable Long user_id) throws Exception {
        boolean userExists = userRepository.existsById(user_id);
        if (userExists){
            return userRepository.findById(user_id);
        }
        throw new Exception("User not found");
    }

}
