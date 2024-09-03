package com.rkisuru.food.recipe.enums;

import lombok.Getter;

@Getter
public enum Gender {

    ANY("Any"),
    MALE("Male"),
    FEMALE("Female");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
