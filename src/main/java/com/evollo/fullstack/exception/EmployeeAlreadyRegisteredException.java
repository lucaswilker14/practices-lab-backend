package com.evollo.fullstack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmployeeAlreadyRegisteredException extends Exception{
    public EmployeeAlreadyRegisteredException(String message) {
        super(message);
    }
}
