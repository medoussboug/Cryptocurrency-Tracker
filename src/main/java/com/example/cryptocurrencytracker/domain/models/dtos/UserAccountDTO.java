package com.example.cryptocurrencytracker.domain.models.dtos;

import com.example.cryptocurrencytracker.domain.models.UserAccountRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.UUID;

public record UserAccountDTO (
        UUID id,
        @NotBlank
        @NotNull
        String username,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$" , message = "Password must contain minimum eight characters, at least one uppercase letter, one lowercase letter and one number")
        String password,
        @Email
        String email,
        @NotBlank
        @NotNull
        String fullName,
        @NotBlank
        @NotNull
        String title,
        @NotBlank
        @NotNull
        String organization,
        LocalDate dateOfBirth,
        LocalDate dateOfCreation,
        UserAccountRole userRole
) {

}

