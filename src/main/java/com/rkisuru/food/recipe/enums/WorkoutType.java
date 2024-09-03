package com.rkisuru.food.recipe.enums;

import lombok.Getter;

@Getter
public enum WorkoutType {

    MIXED("Mixed"),
    CARDIO("Cardio"),
    CALISTHENICS("Calisthenics"),
    BODYBUILDING("Bodybuilding"),
    YOGA("Yoga"),
    POWERLIFTING("Powerlifting");

    private final String displayName;

    WorkoutType(String displayName) {
        this.displayName = displayName;
    }
}
