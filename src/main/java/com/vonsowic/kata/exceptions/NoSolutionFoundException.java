package com.vonsowic.kata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Chain Not Found")
public class NoSolutionFoundException extends RuntimeException{
}
