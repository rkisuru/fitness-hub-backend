package com.rkisuru.fitnesshub.controller;

import com.rkisuru.fitnesshub.entity.User;
import com.rkisuru.fitnesshub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @DeleteMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable Long user_id) throws Exception{
        boolean userExists = userRepository.existsById(user_id);
        if(userExists){
            userRepository.deleteById(user_id);
            return "User deleted successfully";
        }
        throw new Exception("User does not exist");
    }
}
