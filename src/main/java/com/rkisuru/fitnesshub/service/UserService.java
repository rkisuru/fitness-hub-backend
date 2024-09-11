package com.rkisuru.fitnesshub.service;

import com.rkisuru.fitnesshub.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();
    public User findUserById(Long userId) throws Exception;
}
