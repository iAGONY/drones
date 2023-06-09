package com.musala.drones.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }

}
