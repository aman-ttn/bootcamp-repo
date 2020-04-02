package com.bootcamp.bootcampecomproject.exception;

import com.bootcamp.bootcampecomproject.entities.VerificationToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class VerificationTokenInvalid extends RuntimeException {
    public VerificationTokenInvalid(String message){
        super(message);
    }
}
