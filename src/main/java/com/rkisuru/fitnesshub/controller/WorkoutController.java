package com.rkisuru.fitnesshub.controller;

import com.rkisuru.fitnesshub.dto.WorkoutEditRequest;
import com.rkisuru.fitnesshub.dto.WorkoutRequest;
import com.rkisuru.fitnesshub.service.CloudinaryService;
import com.rkisuru.fitnesshub.service.WorkoutService;
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
@RequestMapping("/api/v1/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final CloudinaryService cloudinaryService;


    @PostMapping("/")
    public ResponseEntity<?> createWorkout(@Valid @RequestBody WorkoutRequest request) {

        return ResponseEntity.ok(workoutService.saveWorkout(request));
    }

    @PostMapping(value = "/{workoutId}/cover", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadCoverImage(@PathVariable Long workoutId, @RequestParam MultipartFile file) throws IOException {

        BufferedImage bi = ImageIO.read(file.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>("Invalid image file", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.uploadImage(file);
        workoutService.saveCover(workoutId, (String) result.get("url"), (String) result.get("public_id"));

        return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Map<String, String>> deleteWorkout(@PathVariable Long workoutId, Authentication connectedUser) throws IOException {

        workoutService.deleteWorkout(workoutId, connectedUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "workout deleted successfully");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllWorkouts() {

        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<?> getWorkoutById(@PathVariable Long workoutId, Authentication connectedUser) {

        return ResponseEntity.ok(workoutService.findWorkoutById(workoutId, connectedUser));
    }

    @PutMapping("/{workoutId}")
    public ResponseEntity<?> updateWorkout(@PathVariable Long workoutId, @RequestBody WorkoutEditRequest request, Authentication connectedUser) {

        return ResponseEntity.ok(workoutService.editWorkout(workoutId, request, connectedUser));
    }

    @PatchMapping("/{workoutId}")
    public ResponseEntity<?> likeWorkout(@PathVariable Long workoutId, Authentication connectedUser) {

        return ResponseEntity.ok(workoutService.likeWorkout(workoutId, connectedUser));
    }
}
