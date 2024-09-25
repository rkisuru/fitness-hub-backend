package com.rkisuru.fitnesshub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignUpRequest {

    @NotEmpty(message = "Firstname is required")
    @NotNull(message = "Firstname is required")
    private String firstname;

    @NotEmpty(message = "Firstname is required")
    @NotNull(message = "Firstname is required")
    private String lastname;

    @Email(message = "Enter a valid email address")
    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email is required")
    private String email;

    @Length(min = 4, message = "Password should be at least 4 characters long")
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;

}
