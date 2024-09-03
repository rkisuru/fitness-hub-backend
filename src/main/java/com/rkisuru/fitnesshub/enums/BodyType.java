package com.rkisuru.fitnesshub.enums;

import lombok.Getter;

@Getter
public enum BodyType {

    ANY("Any"),
    ECTOMORPH("Rounded Physique"),
    MOSOMORPH("Athletic Physique"),
    ENDOMORPH("Lean Physique");

    private final String name;

    BodyType(String name) {
        this.name = name;
    }
}
