package com.rkisuru.food.recipe.service;

import com.rkisuru.food.recipe.model.Workout;
import com.rkisuru.food.recipe.model.User;
import com.rkisuru.food.recipe.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public Workout createWorkout(Workout workout, User user){
        Workout createdWorkout = new Workout();
        createdWorkout.setTitle(workout.getTitle());
        createdWorkout.setContent(workout.getContent());
        createdWorkout.setImage(workout.getImage());
        createdWorkout.setType(workout.getType());
        createdWorkout.setUser(user);

        return workoutRepository.save(createdWorkout);
    }

    public Workout findWorkoutById(Long id) throws Exception{
        Optional<Workout> opt = workoutRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        } throw new Exception("Workout not found");
    }


    public Workout updateWorkout(Workout workout, Long id, Authentication user) throws Exception {

        Optional<Workout> opt = workoutRepository.findById(id);
        if(opt.isPresent()){
            Workout optWorkout = opt.get();
            if(!optWorkout.getUser().getUsername().equals(user.getName())){
                throw new RuntimeException("You are not authorized to perform this action");
            }
            optWorkout.setTitle(workout.getTitle());
            optWorkout.setContent(workout.getContent());
            optWorkout.setImage(workout.getImage());
            optWorkout.setType(workout.getType());
            return workoutRepository.save(optWorkout);
        }
        throw new Exception("Workout not found");
    }

    public List<Workout> findAllWorkout(){
        return workoutRepository.findAll();
    }


    public void deleteWorkoutUser(Long id, Authentication user)throws Exception{

        Optional<Workout> opt = workoutRepository.findById(id);
        if(opt.isPresent()){
            Workout workout = opt.get();
            if(!workout.getUser().getUsername().equals(user.getName())){
                throw new RuntimeException("You are not authorized to perform this action");
            }
            workoutRepository.delete(workout);
        } throw new Exception("Workout not found");
    }

    /*public Recipe likeRecipe(Long recipeId, User user) throws Exception{
        Recipe recipe = findRecipeById(recipeId);

        if (recipe.getLikes().contains(user.getId())){
            recipe.getLikes().remove(user.getId());
        }
        else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe);
    }*/
    /*public void deleteWorkoutAdmin(Long id)throws Exception{
        workoutRepository.deleteById(id);
    }*/
}
