package com.rkisuru.food.recipe.service;

import com.rkisuru.food.recipe.model.Recipe;
import com.rkisuru.food.recipe.model.User;
import com.rkisuru.food.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe createRecipe(Recipe recipe, User user){
        Recipe createdRecipe = new Recipe();
        createdRecipe.setRecipeName(recipe.getRecipeName());
        createdRecipe.setRecipeImage(recipe.getRecipeImage());
        createdRecipe.setRecipeContent(recipe.getRecipeContent());
        createdRecipe.setUser(user);
        createdRecipe.setCreatedAt(LocalDateTime.now());

        return recipeRepository.save(createdRecipe);
    }

    public Recipe findRecipeById(Long id) throws Exception{
        Optional<Recipe> opt = recipeRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        } throw new Exception("Recipe not found");
    }

    public void deleteRecipeAdmin(Long id)throws Exception{
        recipeRepository.deleteById(id);
    }

    public void deleteRecipeUser(Long id)throws Exception{

        Recipe recipe = findRecipeById(id);
        String username = getUsername();
        if (!recipe.getUser().getUsername().equals(username)){
            throw new RuntimeException("You are not authorized to perform this action");
        }
        recipeRepository.delete(recipe);
    }

    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Recipe oldRecipe = findRecipeById(id);

        String username = getUsername();
        if (!recipe.getUser().getUsername().equals(username)){
            throw new RuntimeException("You are not authorized to perform this action");
        }
        oldRecipe.setRecipeName(recipe.getRecipeName());
        oldRecipe.setRecipeImage(recipe.getRecipeImage());
        oldRecipe.setRecipeContent(recipe.getRecipeContent());
        return recipeRepository.save(oldRecipe);
    }

    public List<Recipe> findAllRecipe(){
        return recipeRepository.findAll();
    }

    public Recipe likeRecipe(Long recipeId, User user) throws Exception{
        Recipe recipe = findRecipeById(recipeId);

        if (recipe.getLikes().contains(user.getId())){
            recipe.getLikes().remove(user.getId());
        }
        else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe);
    }

    private String getUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
