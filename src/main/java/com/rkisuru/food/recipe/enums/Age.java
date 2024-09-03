package com.rkisuru.food.recipe.enums;

import lombok.Getter;

@Getter
public enum Age {

    ANY("Any"),
    CHILDREN("5-12 years"),
    TEENAGERS("13-18 years"),
    ADULTS("19-54 years"),
    OLDER_ADULTS("55+ years");

    private final String age;

    Age(String age) {
        this.age = age;
    }

}
