package com.rkisuru.fitnesshub.mapper;

import com.rkisuru.fitnesshub.dto.ExerciseRequest;
import com.rkisuru.fitnesshub.dto.WorkoutRequest;
import com.rkisuru.fitnesshub.entity.Exercise;
import com.rkisuru.fitnesshub.entity.Workout;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper {

    public Exercise toExercise (ExerciseRequest request) {

        return Exercise.builder()
                .name(request.name())
                .description(request.description())
                .targetMuscle(request.targetMuscle())
                .build();
    }

    public Workout toWorkout (WorkoutRequest request) {

        return Workout.builder()
                .title(request.title())
                .duration(request.duration())
                .calories(request.calories())
                .bodyType(request.bodyType())
                .age(request.age())
                .workoutType(request.workoutType())
                .gender(request.gender())
                .build();
    }
}
