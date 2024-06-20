package com.phatdo.resourceserver.dto.request;

import com.phatdo.resourceserver.classsify.ApplicationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAccountDTO (
        @NotBlank(message = "Username must not be blank")
        String username,
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,
        @NotBlank(message = "Application name must not be blank")
        String applicationName,
        @NotNull(message = "Type must not be null")
        ApplicationType type) {
}
