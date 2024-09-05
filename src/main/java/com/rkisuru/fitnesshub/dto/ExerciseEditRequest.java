package com.rkisuru.fitnesshub.dto;

public record ExerciseEditRequest(

        String name,
        String targetMuscle,
        String description
) {
}
