package com.rkisuru.food.recipe.controller;

import com.rkisuru.food.recipe.model.Recipe;
import com.rkisuru.food.recipe.model.User;
import com.rkisuru.food.recipe.service.RecipeService;
import com.rkisuru.food.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping("/user/create/{userId}")
    public ResponseEntity<?> saveRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) {
        try {
            User user = userService.findUserById(userId);
            Recipe createdRecipe = recipeService.createRecipe(recipe, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/recipes")
    public ResponseEntity<List<Recipe>> getAllRecipe() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(recipeService.findAllRecipe());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<?> getRecipeById(@PathVariable Long recipeId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(recipeService.findRecipeById(recipeId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/user/update/{recipeId}")
    public ResponseEntity<?> updateRecipe(@PathVariable Long recipeId,@RequestBody Recipe recipe){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(recipeService.updateRecipe(recipe, recipeId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/admin/delete/{recipeId}")
    public void deleteRecipeAdmin(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipeAdmin(recipeId);
    }

    @DeleteMapping("/user/delete/{recipeId}")
    public void deleteRecipeUser(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipeUser(recipeId);
    }

    @PutMapping("/user/like/{recipeId}/{userId}")
    public ResponseEntity<?> likeRecipe(@PathVariable Long recipeId, @PathVariable Long userId){
        try {
            User user = userService.findUserById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(recipeService.likeRecipe(recipeId, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
