package com.rkisuru.food.recipe.repository;

import com.rkisuru.food.recipe.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    Optional<Workout> findById(Long id);
}
