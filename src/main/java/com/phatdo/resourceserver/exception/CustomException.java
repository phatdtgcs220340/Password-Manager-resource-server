package com.phatdo.resourceserver.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {
    private final CustomError error;

    public CustomException(CustomError error) {
        super(error.getMessage());
        this.error = error;
    }
}
