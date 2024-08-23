package com.rkisuru.food.recipe.service;

import com.rkisuru.food.recipe.model.User;
import com.rkisuru.food.recipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public User findUserById(Long userId) throws Exception{
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new Exception("User not found");
    }
}
