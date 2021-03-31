package com.plabs.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyTakenException extends Exception {
    public UserAlreadyTakenException(String message) {
        super(message);
    }
}
