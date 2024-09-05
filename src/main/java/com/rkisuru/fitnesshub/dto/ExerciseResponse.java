package com.rkisuru.fitnesshub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExerciseResponse {

    private String name;
    private String description;
    private String targetMuscle;
    private byte[] image;
}
