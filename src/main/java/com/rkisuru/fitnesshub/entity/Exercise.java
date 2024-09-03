package com.rkisuru.fitnesshub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String image;

    private String targetMuscle;

    private String description;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;
}
