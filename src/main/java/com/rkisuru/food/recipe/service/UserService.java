package com.rkisuru.food.recipe.service;

import com.rkisuru.food.recipe.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();
    public User findUserById(Long userId) throws Exception;

}
