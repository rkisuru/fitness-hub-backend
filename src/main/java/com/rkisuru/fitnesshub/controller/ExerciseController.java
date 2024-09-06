package com.rkisuru.fitnesshub.controller;

import com.rkisuru.fitnesshub.dto.ExerciseEditRequest;
import com.rkisuru.fitnesshub.dto.ExerciseRequest;
import com.rkisuru.fitnesshub.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/workout/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/{workoutId}")
    public ResponseEntity<?> getExercisesByWorkoutId(@PathVariable Long workoutId) {

        return ResponseEntity.ok(exerciseService.getExercisesByWorkoutId(workoutId));
    }

    @PostMapping("/{workoutId}")
    public ResponseEntity<?> addExercise(@RequestBody ExerciseRequest request, @PathVariable Long workoutId, Authentication connectedUser) {

        return ResponseEntity.ok(exerciseService.addExercise(request, workoutId, connectedUser));
    }

    @PutMapping("/{exerciseId}")
    public ResponseEntity<?> updateExercise(@RequestBody ExerciseEditRequest request, @PathVariable Long exerciseId, Authentication connectedUser) {

        return ResponseEntity.ok(exerciseService.editExercise(exerciseId, connectedUser, request));
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long exerciseId, Authentication connectedUser) {

        return ResponseEntity.ok(exerciseService.removeExercise(exerciseId, connectedUser));
    }

    @PostMapping(value = "/{exerciseId}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@PathVariable Long exerciseId, @RequestParam("file") MultipartFile file, Authentication connectedUser) throws IOException {

        return ResponseEntity.ok(exerciseService.uploadImage(exerciseId, file, connectedUser));
    }
}
