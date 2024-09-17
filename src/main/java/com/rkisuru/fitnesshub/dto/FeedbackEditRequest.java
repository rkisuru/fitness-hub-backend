package com.rkisuru.fitnesshub.dto;

import jakarta.validation.constraints.NotNull;

public record FeedbackEditRequest(

        @NotNull
        String feedback
) {
}
