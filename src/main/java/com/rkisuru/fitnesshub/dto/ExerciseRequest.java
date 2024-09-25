package com.rkisuru.fitnesshub.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ExerciseRequest(

        @NotEmpty(message = "Exercise name is required")
        @NotNull(message = "Exercise name is required")
        String name,

        String targetMuscle,
        String description
) {
}
