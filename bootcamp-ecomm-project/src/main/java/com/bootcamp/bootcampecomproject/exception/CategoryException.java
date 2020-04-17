package com.bootcamp.bootcampecomproject.exception;

import com.bootcamp.bootcampecomproject.entities.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryException extends RuntimeException {
    public CategoryException(String message){
        super(message);
    }
}
