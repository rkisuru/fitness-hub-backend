package com.rkisuru.food.recipe.service;

import com.rkisuru.food.recipe.model.Recipe;
import com.rkisuru.food.recipe.model.User;

import java.util.List;

public interface RecipeService {

    public Recipe createRecipe(Recipe recipe, User user);
    public Recipe findRecipeById(Long id) throws Exception;

    public void deleteRecipeAdmin(Long id)throws Exception;

    public void deleteRecipeUser(Long id)throws Exception;

    public Recipe updateRecipe(Recipe recipe, Long id)throws Exception;

    public List<Recipe>findAllRecipe();
    public Recipe likeRecipe(Long recipeId, User user) throws Exception;
}
