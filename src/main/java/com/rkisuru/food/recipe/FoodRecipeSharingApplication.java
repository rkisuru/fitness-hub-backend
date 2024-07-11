package com.rkisuru.food.recipe;

import com.rkisuru.food.recipe.model.User;
import com.rkisuru.food.recipe.repository.UserRepository;
import com.rkisuru.food.recipe.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FoodRecipeSharingApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(FoodRecipeSharingApplication.class, args);
	}

	public void run(String... args){
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if (null==adminAccount){
			User user = new User();

			user.setEmail("admin@gmail.com");
			user.setFullName("Admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
