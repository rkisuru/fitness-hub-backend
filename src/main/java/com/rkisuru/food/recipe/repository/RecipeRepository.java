package com.rkisuru.food.recipe.repository;

import com.rkisuru.food.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
