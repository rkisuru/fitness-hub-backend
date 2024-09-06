package com.rkisuru.fitnesshub.service;

import com.rkisuru.fitnesshub.dto.ExerciseEditRequest;
import com.rkisuru.fitnesshub.dto.ExerciseRequest;
import com.rkisuru.fitnesshub.dto.ExerciseResponse;
import com.rkisuru.fitnesshub.entity.Exercise;
import com.rkisuru.fitnesshub.entity.Workout;
import com.rkisuru.fitnesshub.mapper.DtoMapper;
import com.rkisuru.fitnesshub.repository.ExerciseRepository;
import com.rkisuru.fitnesshub.repository.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final DtoMapper mapper;
    private final WorkoutRepository workoutRepository;
    private final ImageUploadService imageUploadService;

    public Long addExercise(ExerciseRequest request, Long workoutId, Authentication connectedUser) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(()-> new EntityNotFoundException("Workout not found"));

        if (workout.getCreatedBy().equals(connectedUser.getName())) {

            Exercise exercise = mapper.toExercise(request);
            exercise.setWorkout(workout);
            return exerciseRepository.save(exercise).getId();
        }
        throw new AccessDeniedException("Access denied");
    }

    public String removeExercise(Long exerciseId, Authentication connectedUser) {

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(()-> new EntityNotFoundException("Exercise not found"));

        if (exercise.getCreatedBy().equals(connectedUser.getName())) {
            exerciseRepository.delete(exercise);
            return "Exercise removed Successfully";
        }
        throw new AccessDeniedException("Access denied");
    }

    public Exercise editExercise(Long exerciseId, Authentication connectedUser, ExerciseEditRequest request) {


        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(()-> new EntityNotFoundException("Exercise not found"));

        if (exercise.getCreatedBy().equals(connectedUser.getName())) {

            if (!request.name().isEmpty()) {
                exercise.setName(request.name());
            }
            if (!request.description().isEmpty()) {
                exercise.setDescription(request.description());
            }
            if (!request.targetMuscle().isEmpty()) {
                exercise.setTargetMuscle(request.targetMuscle());
            }
            return exerciseRepository.save(exercise);
        }
        throw new AccessDeniedException("Access denied");
    }

    public List<ExerciseResponse> getExercisesByWorkoutId(Long workoutId) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(()-> new EntityNotFoundException("Workout not found"));

        return workout.getExercises()
                .stream()
                .map(mapper::fromExercise)
                .toList();
    }

    public String uploadImage(Long exerciseId, MultipartFile file, Authentication connectedUser) throws IOException {

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(()-> new EntityNotFoundException("Exercise not found"));

        if (exercise.getCreatedBy().equals(connectedUser.getName())) {

            var image = imageUploadService.uploadFile(file);
            exercise.setImage(image);
            exerciseRepository.save(exercise);
            return "Image uploaded successfully";
        }
        throw new AccessDeniedException("Access denied");
    }
}
