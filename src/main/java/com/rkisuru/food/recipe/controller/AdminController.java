package com.rkisuru.food.recipe.controller;

import com.rkisuru.food.recipe.model.User;
import com.rkisuru.food.recipe.repository.UserRepository;
import com.rkisuru.food.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeService recipeService;

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
