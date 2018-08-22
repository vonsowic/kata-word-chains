package com.vonsowic.kata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UnknownWordException extends RuntimeException{
    public UnknownWordException(String message) {
        super(message);
    }

    public UnknownWordException() {
        super();
    }
}
