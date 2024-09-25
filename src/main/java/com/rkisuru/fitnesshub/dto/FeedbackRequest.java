package com.rkisuru.fitnesshub.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FeedbackRequest(

        @NotNull(message = "Enter the feedback")
        @NotEmpty(message = "Enter the feedback")
        String feedback
) {
}
