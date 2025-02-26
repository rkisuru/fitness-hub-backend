package com.rkisuru.fitnesshub.controller;

import com.rkisuru.fitnesshub.dto.ExerciseRequest;
import com.rkisuru.fitnesshub.service.CloudinaryService;
import com.rkisuru.fitnesshub.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final CloudinaryService cloudinaryService;

    @GetMapping("/{workoutId}/exercises")
    public ResponseEntity<?> getExercisesByWorkoutId(@PathVariable Long workoutId) {

        return ResponseEntity.ok(exerciseService.getExercisesByWorkoutId(workoutId));
    }

    @PostMapping("/{workoutId}/exercises")
    public ResponseEntity<?> addExercise(@Valid @RequestBody ExerciseRequest request, @PathVariable Long workoutId, Authentication connectedUser) {

        return ResponseEntity.ok(exerciseService.addExercise(request, workoutId, connectedUser));
    }

    @PatchMapping("/{exerciseId}")
    public ResponseEntity<?> updateExercise(@RequestBody ExerciseRequest request, @PathVariable Long exerciseId, Authentication connectedUser) {

        return ResponseEntity.ok(exerciseService.editExercise(exerciseId, connectedUser, request));
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<Map<String, String>> deleteExercise(@PathVariable Long exerciseId, Authentication connectedUser) {

        exerciseService.removeExercise(exerciseId, connectedUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Exercise deleted successfully");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/{exerciseId}/image")
    public ResponseEntity<?> uploadExerciseImage(@PathVariable Long exerciseId, @RequestParam("file") MultipartFile file) throws IOException {

        BufferedImage bi = ImageIO.read(file.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>("Invalid image file", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.uploadImage(file);
        exerciseService.saveImage(exerciseId, (String) result.get("url"), (String) result.get("public_id"));

        return ResponseEntity.ok(result);
    }
}
