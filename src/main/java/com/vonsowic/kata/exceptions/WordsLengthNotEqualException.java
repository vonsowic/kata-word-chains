package com.vonsowic.kata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Words must have the same length")
public class WordsLengthNotEqualException extends RuntimeException { }
