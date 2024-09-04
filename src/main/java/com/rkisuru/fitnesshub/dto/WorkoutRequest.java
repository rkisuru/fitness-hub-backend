package com.rkisuru.fitnesshub.dto;

import com.rkisuru.fitnesshub.enums.Age;
import com.rkisuru.fitnesshub.enums.BodyType;
import com.rkisuru.fitnesshub.enums.Gender;
import com.rkisuru.fitnesshub.enums.WorkoutType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record WorkoutRequest(

        @NotNull
        @NotEmpty
        String title,
        String duration,
        Integer calories,
        @NotNull
        @NotEmpty
        BodyType bodyType,
        @NotNull
        @NotEmpty
        Age age,
        @NotNull
        @NotEmpty
        WorkoutType workoutType,
        @NotNull
        @NotEmpty
        Gender gender
) {
}
