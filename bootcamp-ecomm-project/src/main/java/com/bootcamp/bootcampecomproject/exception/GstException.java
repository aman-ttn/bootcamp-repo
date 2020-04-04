package com.bootcamp.bootcampecomproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GstException extends RuntimeException {
    public GstException(String message){super(message);}
}
