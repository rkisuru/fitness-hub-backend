package com.rkisuru.fitnesshub.service;

import com.rkisuru.fitnesshub.dto.WorkoutRequest;
import com.rkisuru.fitnesshub.dto.WorkoutResponse;
import com.rkisuru.fitnesshub.entity.Exercise;
import com.rkisuru.fitnesshub.entity.Workout;
import com.rkisuru.fitnesshub.mapper.DtoMapper;
import com.rkisuru.fitnesshub.repository.ExerciseRepository;
import com.rkisuru.fitnesshub.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final DtoMapper mapper;
    private final ExerciseRepository exerciseRepository;

    public Long saveWorkout(WorkoutRequest request) {

        Workout workout = mapper.toWorkout(request);
        return workoutRepository.save(workout).getId();
    }

    public String deleteWorkout(Long id, Authentication connectedUser) {

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        if (workout.getCreatedBy().equals(connectedUser.getName())) {

            List<Exercise> exercises = workout.getExercises();
            exerciseRepository.deleteAll(exercises);
            workoutRepository.delete(workout);
            return "Workout deleted Successfully";
        }
        throw new AccessDeniedException("Access denied");
    }
}
