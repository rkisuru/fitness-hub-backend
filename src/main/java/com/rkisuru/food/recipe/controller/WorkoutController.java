package com.rkisuru.food.recipe.controller;

import com.rkisuru.food.recipe.model.Workout;
import com.rkisuru.food.recipe.model.User;
import com.rkisuru.food.recipe.service.WorkoutService;
import com.rkisuru.food.recipe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    private final UserService userService;

    @PostMapping("/user/create/{userId}")
    public ResponseEntity<?> saveWorkout(@RequestBody Workout workout, @PathVariable Long userId) {
        try {
            User user = userService.findUserById(userId);
            return ResponseEntity.ok(workoutService.createWorkout(workout, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/recipes")
    public ResponseEntity<List<Workout>> getAllWorkout() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(workoutService.findAllWorkout());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/recipe/{workoutId}")
    public ResponseEntity<?> getWorkoutById(@PathVariable Long workoutId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(workoutService.findWorkoutById(workoutId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/user/update/{workoutId}")
    public ResponseEntity<?> updateWorkout(@PathVariable Long workoutId, @RequestBody Workout workout, Authentication user){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(workoutService.updateWorkout(workout, workoutId, user));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/user/delete/{workoutId}")
    public void deleteRecipeUser(@PathVariable Long workoutId, Authentication user) throws Exception {
        workoutService.deleteWorkoutUser(workoutId, user);
    }

    /*@DeleteMapping("/admin/delete/{recipeId}")
    public void deleteRecipeAdmin(@PathVariable Long recipeId) throws Exception {
        workoutService.deleteRecipeAdmin(recipeId);
    }
*/
    /*@PutMapping("/user/like/{recipeId}/{userId}")
    public ResponseEntity<?> likeRecipe(@PathVariable Long recipeId, @PathVariable Long userId){
        try {
            User user = userService.findUserById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(workoutService.likeRecipe(recipeId, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/
}
