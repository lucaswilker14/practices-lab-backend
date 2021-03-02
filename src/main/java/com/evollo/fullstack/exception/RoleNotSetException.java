package com.evollo.fullstack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotSetException extends Exception {
    public RoleNotSetException(String message) {
        super(message);
    }
}
