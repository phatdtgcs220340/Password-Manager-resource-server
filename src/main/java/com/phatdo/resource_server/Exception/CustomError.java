package com.phatdo.resource_server.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomError {
    ACCOUNT_NOT_FOUND("Account not found", HttpStatus.NOT_FOUND),
    ACCOUNT_IS_EXISTED("Account already exists", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus code;

    CustomError(String message, HttpStatus code) {
        this.message = message;
        this.code = code;
    }
}
