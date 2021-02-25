package com.example.vulnerable.controller.handler;

import com.example.vulnerable.controller.exceptions.ApiException;
import com.example.vulnerable.service.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> apiExeptionHandler(final ApiException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> notFoundExeptionHandler(ServiceException e){
        return new ResponseEntity<>("User does not exists.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> internalServerError(Exception e) {
        return new ResponseEntity<>("Something went wrong." + " reason: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
