package com.rkisuru.fitnesshub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FeedbackRequest(

        @NotNull(message = "Feedback cannot be null")
        @NotEmpty(message = "Feedback cannot be empty")
        @NotBlank(message = "Feedback cannot be blank")
        String feedback
) {
}
