package com.rkisuru.fitnesshub.dto;

import jakarta.validation.constraints.NotNull;

public record FeedbackRequest(

        @NotNull
        String feedback
) {
}
