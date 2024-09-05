package com.rkisuru.fitnesshub.mapper;

import com.rkisuru.fitnesshub.dto.ExerciseRequest;
import com.rkisuru.fitnesshub.dto.ExerciseResponse;
import com.rkisuru.fitnesshub.dto.WorkoutRequest;
import com.rkisuru.fitnesshub.dto.WorkoutResponse;
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

    public ExerciseResponse fromExercise(Exercise exercise) {

        return ExerciseResponse.builder()
                .name(exercise.getName())
                .description(exercise.getDescription())
                .targetMuscle(exercise.getTargetMuscle())
                .image(exercise.getImage().getBytes())
                .build();
    }

    public WorkoutResponse fromWorkout(Workout workout) {

        return WorkoutResponse.builder()
                .title(workout.getTitle())
                .duration(workout.getDuration())
                .calories(workout.getCalories())
                .workoutType(workout.getWorkoutType())
                .bodyType(workout.getBodyType())
                .age(workout.getAge())
                .gender(workout.getGender())
                .coverImage(workout.getCoverImage().getBytes())
                .exercises(workout.getExercises())
                .comments(workout.getComments())
                .createdBy(workout.getCreatedBy())
                .createdAt(workout.getCreatedAt())
                .modifiedAt(workout.getModifiedAt())
                .build();
    }
}
