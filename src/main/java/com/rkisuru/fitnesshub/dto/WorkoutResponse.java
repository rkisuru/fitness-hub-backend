package com.rkisuru.fitnesshub.dto;

import com.rkisuru.fitnesshub.entity.Comment;
import com.rkisuru.fitnesshub.entity.Exercise;
import com.rkisuru.fitnesshub.enums.Age;
import com.rkisuru.fitnesshub.enums.BodyType;
import com.rkisuru.fitnesshub.enums.Gender;
import com.rkisuru.fitnesshub.enums.WorkoutType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkoutResponse {

    private String title;
    private String duration;
    private Integer calories;
    private byte[] coverImage;
    private BodyType bodyType;
    private Age age;
    private WorkoutType workoutType;
    private Gender gender;
    private List<Exercise> exercises;
    private List<Comment> comments;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
}
