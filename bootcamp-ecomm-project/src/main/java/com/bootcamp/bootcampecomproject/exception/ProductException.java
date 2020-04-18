package com.bootcamp.bootcampecomproject.exception;

import com.bootcamp.bootcampecomproject.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductException extends RuntimeException {
    public ProductException(String message){
        super(message);
    }
}
