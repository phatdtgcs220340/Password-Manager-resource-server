package com.phatdo.resourceserver.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAccountDTO (
        @NotNull(message = "Type must not be null")
        String id,
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String newPassword) {
}
