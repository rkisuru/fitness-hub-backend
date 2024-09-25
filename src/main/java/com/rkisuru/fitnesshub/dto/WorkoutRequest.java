package com.rkisuru.fitnesshub.dto;

import com.rkisuru.fitnesshub.enums.Age;
import com.rkisuru.fitnesshub.enums.BodyType;
import com.rkisuru.fitnesshub.enums.Gender;
import com.rkisuru.fitnesshub.enums.WorkoutType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record WorkoutRequest(

        @NotNull(message = "Title cannot be empty")
        @NotEmpty(message = "Title cannot be empty")
        String title,

        String duration,
        Integer calories,
        BodyType bodyType,
        Age age,
        WorkoutType workoutType,
        Gender gender
) {
}
