package com.rkisuru.fitnesshub.dto;

import com.rkisuru.fitnesshub.enums.Age;
import com.rkisuru.fitnesshub.enums.BodyType;
import com.rkisuru.fitnesshub.enums.Gender;
import com.rkisuru.fitnesshub.enums.WorkoutType;

public record WorkoutEditRequest(

        String title,
        String duration,
        Integer calories,
        BodyType bodyType,
        Age age,
        WorkoutType workoutType,
        Gender gender
) {
}
