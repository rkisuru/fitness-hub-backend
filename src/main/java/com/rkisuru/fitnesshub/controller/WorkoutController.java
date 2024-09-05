package com.rkisuru.fitnesshub.controller;

import com.rkisuru.fitnesshub.dto.ExerciseRequest;
import com.rkisuru.fitnesshub.dto.WorkoutRequest;
import com.rkisuru.fitnesshub.service.ExerciseService;
import com.rkisuru.fitnesshub.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    @PostMapping("/create")
    public ResponseEntity<?> createWorkout(@RequestBody WorkoutRequest request) {

        return ResponseEntity.ok(workoutService.saveWorkout(request));
    }

    @PostMapping("/create/{workoutId}")
    public ResponseEntity<?> createExercise(@RequestBody ExerciseRequest request, Authentication connectedUser, @PathVariable Long workoutId) {

        return ResponseEntity.ok(exerciseService.addExercise(request, workoutId, connectedUser));
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long workoutId, Authentication connectedUser) {

        return ResponseEntity.ok(workoutService.deleteWorkout(workoutId, connectedUser));
    }

    @GetMapping
    public ResponseEntity<?> getAllWorkouts() {

        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<?> getWorkoutById(@PathVariable Long workoutId) {

        return ResponseEntity.ok(workoutService.findWorkoutById(workoutId));
    }
}
