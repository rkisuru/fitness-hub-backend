package com.rkisuru.fitnesshub.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ExerciseRequest(

        @NotEmpty
        @NotNull
        String name,
        String targetMuscle,
        String description
) {
}
