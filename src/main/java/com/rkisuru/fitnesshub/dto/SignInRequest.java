package com.rkisuru.fitnesshub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignInRequest {

    @Email(message = "Enter a valid email")
    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Length(min = 4, message = "Password must at least 4 characters long")
    private String password;

}
